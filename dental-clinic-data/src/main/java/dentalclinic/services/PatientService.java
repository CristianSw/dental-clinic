package dentalclinic.services;

import dentalclinic.model.Patient;

import java.util.List;
import java.util.Set;


public interface PatientService extends CrudService<Patient, Long> {
    Patient findByLastName(String lastName);
    List<Patient> findByLastNameLike(String lastName);
}
