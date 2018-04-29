package datacenter.mqtt;

public class MqttTest {

    public static void main(String[] args) {
        MqttTopicPublisher pub = new MqttTopicPublisher("myTest");
        MqttTopicSubscriber sub1 = new MqttTopicSubscriber("myTest");
        MqttTopicSubscriber sub2 = new MqttTopicSubscriber("myTest");

        sub1.bindTopic("*.warning");

        sub2.bindTopic("advice.#");

        pub.publishMessage("test1 (solo sub 2)", "advice.mypatient.abcd");
        pub.publishMessage("test2 (solo sub 1)", "mypatient.warning");
        pub.publishMessage("test3 (sub1 sub 2)", "advice.warning");
        pub.publishMessage("test4 (nessuno)", "mypatient.advice.abcd");
        pub.publishMessage("test5 (nessuno)", "mypatient.abcd");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pub.close();
        sub1.close();
        sub2.close();
    }


}
