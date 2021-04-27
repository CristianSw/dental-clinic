package dentalclinic.services.springdatajpa;

import dentalclinic.model.Doctor;
import dentalclinic.repositories.DoctorRepository;
import dentalclinic.services.DoctorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("springdatajpa")
public class DoctorSDJPAService implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorSDJPAService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Set<Doctor> findAll() {
        Set<Doctor> doctors = new HashSet<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    @Override
    public Doctor findById(Long aLong) {
        return doctorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Doctor save(Doctor object) {
        return doctorRepository.save(object);
    }

    @Override
    public void delete(Doctor object) {
        doctorRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        doctorRepository.deleteById(aLong);
    }

    @Override
    public Doctor findByLastName(String lastName) {
        return doctorRepository.findByLastName(lastName);
    }
}
