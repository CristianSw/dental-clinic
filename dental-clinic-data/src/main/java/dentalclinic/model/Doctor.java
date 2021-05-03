package dentalclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor extends Person {
    @Builder
    public Doctor(Long id, String firstName, String lastName, Integer cabinetNumber) {
        super(id, firstName, lastName);
        this.cabinetNumber = cabinetNumber;
    }

    @Column(name = "cabinet_number")
    private Integer cabinetNumber;
}
