package asw.edipogram.connessioni.event;

public interface ConnessioniDomainEventPublisher {

    public void publish(DomainEvent event);

}
