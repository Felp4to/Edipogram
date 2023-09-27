package asw.edipogram.enigmiseguiti.event;

import asw.edipogram.enigmiseguiti.domain.Connessione;
import asw.edipogram.enigmiseguiti.domain.Enigma;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnigmiSeguitiEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(EnigmiSeguitiEventHandler.class.toString());

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    public void onEvent(DomainEvent event) {
        if (event.getClass().equals(EnigmaCreatedEvent.class)) {
            logger.info("EnigmaCreatedEvent received: " + event);
            EnigmaCreatedEvent e = (EnigmaCreatedEvent) event;
            Enigma enigma = this.createEnigma(e);
            enigmiSeguitiService.addEnigmiSeguitiByEnigma(enigma);
        } else if (event.getClass().equals(ConnessioneCreatedEvent.class)) {
            logger.info("ConnessioneCreatedEvent received: " + event);
            ConnessioneCreatedEvent e = (ConnessioneCreatedEvent) event;
            Connessione connessione = this.createConnessione(e);
            enigmiSeguitiService.addEnigmiSeguitiByConnessione(connessione);
        } else {
            logger.info("Event not supported: " + event.getClass());
        }
    }

    /* Crea un enigma */
    private Enigma createEnigma(EnigmaCreatedEvent event) {
        Enigma enigma = new Enigma(
                event.getId(),
                event.getAutore(),
                event.getTipo(),
                event.getTipoSpecifico(),
                event.getTitolo(),
                event.getTesto()
        );
        enigmiSeguitiService.addEnigma(enigma);
        logger.info("Enigma created: " + enigma);

        return enigma;
    }

    /* Crea una connessione */
    private Connessione createConnessione(ConnessioneCreatedEvent event) {
        Connessione connessione = new Connessione(
                event.getId(),
                event.getUtente(),
                event.getTipo()
        );
        enigmiSeguitiService.addConnessione(connessione);
        logger.info("Connessione created: " + connessione);

        return connessione;
    }
}
