package dentalclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Patient extends Person {
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;

}
