package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.*;

@Service
public class EnigmiSeguitiService {

    private final Logger logger = Logger.getLogger(EnigmiSeguitiService.class.toString());
    
    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private EnigmiRepository enigmiRepository;

    @Autowired
    private EnigmiSeguitiRepository enigmiSeguitiRepository;

    public Collection<EnigmiSeguiti> getEnigmiSeguiti(String utente) {
        return enigmiSeguitiRepository.findByUtente(utente);
    }

    /* Aggiunge un enigma al db */
    public void addEnigma(Enigma enigma) {
        enigmiRepository.save(enigma);
    }

    /* Aggiunge una connessione al db */
    public void addConnessione(Connessione connessione) {
        connessioniRepository.save(connessione);
    }

    /* Aggiunge gli enigmi seguiti al db alla ricezione di EnigmaCreatedEvent */
    public void addEnigmiSeguitiByEnigma(Enigma enigma) {
        String tipo = enigma.getTipo();
        Collection<Connessione> connessioni = connessioniRepository.findByTipo(tipo);
        connessioni.stream()
                .map(Connessione::getUtente)
                .forEach(u -> {
                    EnigmiSeguiti e = new EnigmiSeguiti(
                            u,
                            enigma.getId(),
                            enigma.getAutore(),
                            enigma.getTipo(),
                            enigma.getTipoSpecifico(),
                            enigma.getTitolo(),
                            enigma.getTesto()
                    );
                    enigmiSeguitiRepository.save(e);
                });
        logger.info(connessioni.toString());
    }

    /* Aggiunge gli enigmi seguiti al db alla ricezione di ConnessioneCreatedEvent */
    public void addEnigmiSeguitiByConnessione(Connessione connessione) {
        String tipo = connessione.getTipo();
        Collection<Enigma> enigmi = enigmiRepository.findByTipo(tipo);
        enigmi.forEach(e -> {
            EnigmiSeguiti enigmiSeguiti = new EnigmiSeguiti(
                    connessione.getUtente(),
                    e.getId(),
                    e.getAutore(),
                    e.getTipo(),
                    e.getTipoSpecifico(),
                    e.getTitolo(),
                    e.getTesto()
            );
            enigmiSeguitiRepository.save(enigmiSeguiti);
        });
        logger.info(enigmi.toString());
    }
}
