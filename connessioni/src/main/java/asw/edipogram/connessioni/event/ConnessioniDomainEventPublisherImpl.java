package asw.edipogram.connessioni.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class ConnessioniDomainEventPublisherImpl implements ConnessioniDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;
    private final String channel = ConnessioniServiceEventChannel.CHANNEL;

    @Override
    public void publish(DomainEvent event) {
        template.send(this.channel, event);
    }
}
