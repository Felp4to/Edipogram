package asw.edipogram.enigmi.event;

import asw.edipogram.enigmi.event.DomainEvent;

public interface EnigmaDomainEventPublisher {

    public void publish(DomainEvent event);

}
