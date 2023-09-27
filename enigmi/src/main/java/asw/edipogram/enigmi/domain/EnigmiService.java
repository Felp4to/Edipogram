package asw.edipogram.enigmi.domain;

import asw.edipogram.enigmi.event.DomainEvent;
import asw.edipogram.enigmi.event.EnigmaCreatedEvent;
import asw.edipogram.enigmi.event.EnigmaDomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
@Transactional
public class EnigmiService {

	@Autowired
	private EnigmiRepository enigmiRepository;

	@Autowired
	private EnigmaDomainEventPublisher domainEventPublisher;

	public Enigma createEnigma(String autore, String tipo, String tipoSpecifico, String titolo, String[] testo, String[] soluzione) {
		Enigma enigma = new Enigma(autore, tipo, tipoSpecifico, titolo, testo, soluzione);
		enigma = enigmiRepository.save(enigma);
		DomainEvent event = new EnigmaCreatedEvent(
				enigma.getId(),
				enigma.getAutore(),
				enigma.getTipo(),
				enigma.getTipoSpecifico(),
				enigma.getTitolo(),
				enigma.getTesto()
		);
		domainEventPublisher.publish(event);
		return enigma;
	}

 	public Enigma getEnigma(Long id) {
		Enigma enigma = enigmiRepository.findById(id).orElse(null);
		return enigma;
	}

	public Collection<Enigma> getEnigmi() {
		Collection<Enigma> enigmi = enigmiRepository.findAll();
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutore(String autore) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutore(autore);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutoreIn(autori);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo) {
		Collection<Enigma> enigmi = enigmiRepository.findByTipo(tipo);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi) {
		Collection<Enigma> enigmi = enigmiRepository.findByTipoIn(tipi);
		return enigmi;
	}

}
