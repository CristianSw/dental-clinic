package dentalclinic.services;

import dentalclinic.model.Doctor;



public interface DoctorService extends CrudService<Doctor, Long> {
    Doctor findByLastName(String lastName);
}
