package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EnigmiSeguitiId.class)
public class EnigmiSeguiti {

    @Id
    private String utente;
    @Id
    private Long idEnigma;
    private String autoreEnigma;
    private String tipoEnigma;
    private String tipoSpecificoEnigma;
    private String titoloEnigma;
    private String[] testoEnigma;
}
