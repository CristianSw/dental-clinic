package dentalclinic.services;

import dentalclinic.model.Patient;


public interface PatientService extends CrudService<Patient, Long> {
    Patient findByLastName(String lastName);
}
