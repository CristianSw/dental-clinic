package dentalclinic.services.map;

import dentalclinic.model.Patient;
import dentalclinic.services.PatientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class PatientServiceMap extends AbstractMapService<Patient, Long> implements PatientService {
    @Override
    public Set<Patient> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Patient object) {
        super.delete(object);
    }

    @Override
    public Patient save(Patient object) {
        return super.save(object);
    }

    @Override
    public Patient findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Patient findByLastName(String lastName) {

        return  this.findAll()
                .stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Patient> findByLastNameLike(String lastName) {
        return null;
    }
}
