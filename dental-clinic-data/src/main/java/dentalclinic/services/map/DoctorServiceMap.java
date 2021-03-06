package dentalclinic.services.map;

import dentalclinic.model.Doctor;
import dentalclinic.services.DoctorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default", "map"})
public class DoctorServiceMap extends AbstractMapService<Doctor, Long> implements DoctorService {

    @Override
    public Set<Doctor> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Doctor object) {
        super.delete(object);
    }

    @Override
    public Doctor save(Doctor object) {
        return super.save(object);
    }

    @Override
    public Doctor findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Doctor findByLastName(String lastName) {
        return  this.findAll()
                .stream()
                .filter(doctor -> doctor.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
