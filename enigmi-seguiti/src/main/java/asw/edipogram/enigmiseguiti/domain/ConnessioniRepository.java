package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioniRepository extends CrudRepository<Connessione, Long> {

    public Collection<Connessione> findAll();

    public Collection<Connessione> findByUtente(String utente);

    public Collection<Connessione> findByTipo(String tipo);

}

