package dentalclinic.services.map;

import dentalclinic.model.Patient;
import dentalclinic.services.PatientService;

import java.util.Set;

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
        return super.save(object.getId(), object);
    }

    @Override
    public Patient findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Patient findByLastName(String lastName) {
        return null;
    }
}
