package core.pubsub.subscriber;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import core.pubsub.core.TopicSubscribe;
import core.pubsub.message.AdviceRequestMessage;
import core.pubsub.message.MessagesUtils;
import core.pubsub.publisher.MultiAdvicePublisherActor;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdviceRequestActor extends AbstractActor {

    private static final String QUEUE_NAME= "adviceRequest.queue";
    private static final String EXCHANGE_NAME = "adviceRequest";
    private static final List<String> ROUTING_KEY_ADVICE = Arrays.asList("datacentre.request.advice");

    private static final String HOST_IP = "213.209.230.94";
    private static final int PORT = 8088;

    private final MessagesUtils utils = new MessagesUtils();
    private final ActorRef multiAdvicePublisherActor = ActorSystem.apply("datacentre").actorOf(Props.create(MultiAdvicePublisherActor.class), "multiAdviceActor");

    @Override
    public void preStart() throws Exception {
        super.preStart();

        TopicSubscribe subscribe = new TopicSubscribe(EXCHANGE_NAME, QUEUE_NAME, ROUTING_KEY_ADVICE, HOST_IP, PORT);

        Consumer consumer = new DefaultConsumer(subscribe.getChannel()) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                JSONObject json;
                try {
                    //String body = utils.getBody(message, ROUTING_KEY_ADVICE);
                    //JSONObject json = new JSONObject(body);
                    json = new JSONObject(message);

                    String patientId = json.getString("patientId");
                    String start = json.getString("start");
                    String end =  json.getString("end");


                    AdviceRequestMessage adviceRequest = new AdviceRequestMessage(patientId, start, end);

                    multiAdvicePublisherActor.tell(adviceRequest, multiAdvicePublisherActor );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        subscribe.setConsumer(consumer);





        //AdviceRequest subscriber = new AdviceRequest();
        System.out.println(" -----> AdviceReceiver STARTED.");
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder().build();
    }

}
