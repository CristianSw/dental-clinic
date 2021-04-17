package dentalclinic.services;

import dentalclinic.model.Doctor;


import java.util.Set;

public interface DoctorService {
    Doctor findByLastName(String lastName);

    Doctor findById(Long id);

    Doctor save(Doctor doctor);

    Set<Doctor> findAll();
}
