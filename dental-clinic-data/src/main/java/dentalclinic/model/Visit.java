package dentalclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "local_date")
    private LocalDate localDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
