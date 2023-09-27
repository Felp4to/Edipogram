package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import java.io.Serializable;

@Data @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnigmiSeguitiId implements Serializable {

    private String utente;
    private Long idEnigma;
}
