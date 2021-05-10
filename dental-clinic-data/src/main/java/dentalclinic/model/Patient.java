package dentalclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends Person {
    @Builder
    public Patient(Long id, String firstName, String lastName, String phoneNumber, LocalDate birthDate, String address,
                   Set<Visit> visits) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        if (visits == null || visits.size() > 0) {
            this.visits = visits;
        }
    }

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "adress")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<Visit> visits = new HashSet<>();
}
