package dentalclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Visit extends BaseEntity {
    private LocalDate localDate;
    private String description;
    private Patient patient;
}
