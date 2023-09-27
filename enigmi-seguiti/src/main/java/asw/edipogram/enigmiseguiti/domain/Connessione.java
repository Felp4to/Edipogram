package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Connessione implements Comparable<Connessione>{

	@Id
	@EqualsAndHashCode.Include
	private Long id; 
	private String utente; 
	private String tipo; 

	public Connessione(Long id, String utente, String tipo) {
		this();
		this.id = id;
		this.utente = utente;
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Connessione other) { return this.id.compareTo(other.id); }
}
