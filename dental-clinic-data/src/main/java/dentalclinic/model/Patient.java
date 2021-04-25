package dentalclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends Person {
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "adress")
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

}
