package dentalclinic.services.map;

import dentalclinic.model.Doctor;
import dentalclinic.services.DoctorService;

import java.util.Set;

public class DoctorServiceMap extends AbstractMapService<Doctor, Long> implements DoctorService {

    @Override
    public Set<Doctor> findAll() {
        return this.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    @Override
    public void delete(Doctor object) {
        this.delete(object);
    }

    @Override
    public Doctor save(Doctor object) {
        return this.save(object);
    }

    @Override
    public Doctor findById(Long id) {
        return this.findById(id);
    }

    @Override
    public Doctor findByLastName(String lastName) {
        return null;
    }
}
