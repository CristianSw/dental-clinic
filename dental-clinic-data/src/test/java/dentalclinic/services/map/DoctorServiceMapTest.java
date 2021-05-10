package dentalclinic.services.map;

import dentalclinic.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceMapTest {
    DoctorServiceMap doctorServiceMap;
    final Long doctorId = 1L;
    final String lastName = "Some LastName";


    @BeforeEach
    void setUp() {
        doctorServiceMap = new DoctorServiceMap();
        doctorServiceMap.save(Doctor.builder().id(doctorId).lastName(lastName).build());

    }

    @Test
    void findAll() {
        Set<Doctor> doctors = doctorServiceMap.findAll();
        assertEquals(doctorId, doctors.size());
    }

    @Test
    void deleteById() {
        doctorServiceMap.deleteById(doctorId);
        assertEquals(0, doctorServiceMap.findAll().size());
    }

    @Test
    void delete() {
        doctorServiceMap.delete(doctorServiceMap.findById(doctorId));
        assertEquals(0, doctorServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Long id = 2L;
        Doctor doctor = Doctor.builder().id(id).build();
        Doctor savedDoctor = doctorServiceMap.save(doctor);
        assertEquals(id, savedDoctor.getId());
        assertNotNull(savedDoctor);
    }

    @Test
    void saveWithoutId() {
        Doctor doctor = Doctor.builder().build();
        Doctor savedDoctor = doctorServiceMap.save(doctor);
        assertNotNull(savedDoctor);
        assertNotNull(savedDoctor.getId());
    }

    @Test
    void findById() {
        Doctor doctor = doctorServiceMap.findById(doctorId);
        assertEquals(doctorId, doctor.getId());
    }

    @Test
    void findByLastName() {
        Doctor doctor = doctorServiceMap.findByLastName(lastName);
        assertEquals(lastName, doctor.getLastName());
        assertNotNull(doctor);
    }

    @Test
    void findByLastNameNotFound() {
        String last = "last";
        Doctor doctor = doctorServiceMap.findByLastName(last);
        assertNull(doctor);
    }

}