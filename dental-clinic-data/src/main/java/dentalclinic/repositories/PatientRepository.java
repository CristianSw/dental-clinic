package dentalclinic.repositories;

import dentalclinic.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByLastName(String lastName);
}
