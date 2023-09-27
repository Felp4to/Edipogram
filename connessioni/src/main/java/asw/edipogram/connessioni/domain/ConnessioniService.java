package asw.edipogram.connessioni.domain;

import asw.edipogram.connessioni.event.ConnessioneCreatedEvent;
import asw.edipogram.connessioni.event.ConnessioniDomainEventPublisher;
import asw.edipogram.connessioni.event.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;
import java.util.*;

@Service
public class ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private ConnessioniDomainEventPublisher domainEventPublisher;

    public Connessione createConnessione(String utente, String tipo) {
        Connessione connessione = new Connessione(utente, tipo);
        connessione = connessioniRepository.save(connessione);
        DomainEvent event = new ConnessioneCreatedEvent(
                connessione.getId(),
                connessione.getUtente(),
                connessione.getTipo());
		domainEventPublisher.publish(event);
        return connessione;
    }

    public Connessione getConnessione(Long id) {
        Connessione connessione = connessioniRepository.findById(id).orElse(null);
        return connessione;
    }

    public Collection<Connessione> getConnessioni() {
        Collection<Connessione> connessioni = connessioniRepository.findAll();
        return connessioni;
    }

    public Collection<Connessione> getConnessioniByUtente(String utente) {
        Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente);
        return connessioni;
    }

}
