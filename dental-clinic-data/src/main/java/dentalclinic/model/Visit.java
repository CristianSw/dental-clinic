package dentalclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Builder
    public Visit(Long id, LocalDate localDate, String description, Patient patient) {
        super(id);
        this.localDate = localDate;
        this.description = description;
        this.patient = patient;
    }

    @Column(name = "local_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate localDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
