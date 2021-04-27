package dentalclinic.services.springdatajpa;

import dentalclinic.model.Patient;
import dentalclinic.repositories.PatientRepository;
import dentalclinic.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PatientSDJPAService implements PatientService {

    private final PatientRepository patientRepository;

    public PatientSDJPAService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Set<Patient> findAll() {

        Set<Patient> patients = new HashSet<>();

        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    @Override
    public Patient findById(Long aLong) {
        return patientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Patient save(Patient object) {
        return patientRepository.save(object);
    }

    @Override
    public void delete(Patient object) {
        patientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        patientRepository.deleteById(aLong);
    }

    @Override
    public Patient findByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }
}
