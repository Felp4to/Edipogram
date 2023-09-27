package asw.edipogram.enigmiseguiti.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnessioneCreatedEvent implements DomainEvent {

    private Long id;
    private String utente;
    private String tipo;

}
