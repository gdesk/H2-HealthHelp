package core.pubsub.subscriber;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Created by lucch on 07/06/2018.
 */
public class DrugReceiverActor extends AbstractActor {

    @Override
    public void preStart() throws Exception {
        super.preStart();
        DrugReceiver drugReceiver = new DrugReceiver();
        System.out.println(" -----> DrugReceierActor");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
