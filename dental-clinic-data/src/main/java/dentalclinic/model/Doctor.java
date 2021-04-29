package dentalclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
