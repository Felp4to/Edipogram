package asw.edipogram.enigmi.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnigmaDomainEventPublisherImpl implements EnigmaDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;
    private String channel = EnigmaServiceEventChannel.CHANNEL;

    @Override
    public void publish(DomainEvent event) {
        template.send(channel, event);
    }

}
