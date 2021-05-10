package dentalclinic.repositories;

import dentalclinic.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByLastName(String lastName);
    List<Patient> findByLastNameLike(String lastName);
}
