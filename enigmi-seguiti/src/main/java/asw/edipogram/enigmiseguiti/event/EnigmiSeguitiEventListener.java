package asw.edipogram.enigmiseguiti.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiSeguitiEventListener {

    @Autowired
    private EnigmiSeguitiEventHandler enigmiSeguitiEventHandler;
    
    private final String enigmiChannel = EnigmiEventChannel.channel;
    private final String connessioniChannel = ConnessioniEventChannel.channel;

    @KafkaListener(topics = {enigmiChannel, connessioniChannel}, groupId = "Group1")
    public void listen(ConsumerRecord<String, DomainEvent> record) {
        DomainEvent event = record.value();
        enigmiSeguitiEventHandler.onEvent(event);
    }

}
