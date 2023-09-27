package asw.edipogram.enigmi.domain;

import asw.edipogram.enigmi.event.DomainEvent;
import asw.edipogram.enigmi.event.EnigmaServiceEventChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.*; 

public interface EnigmiRepository extends CrudRepository<Enigma, Long> {

	public Collection<Enigma> findAll();

	public Collection<Enigma> findByAutore(String autore);

	public Collection<Enigma> findByAutoreIn(Collection<String> autori);

	public Collection<Enigma> findByTipo(String tipo);

	public Collection<Enigma> findByTipoIn(Collection<String> tipi);

}

