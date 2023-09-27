package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiRepository extends CrudRepository<Enigma, Long> {

    public Collection<Enigma> findByTipo(String tipo);
}
