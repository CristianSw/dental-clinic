package dentalclinic.services.map;

import dentalclinic.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceMapTest {
    PatientServiceMap patientServiceMap;
    final Long patientId = 1L;
    final String lastName = "Some LastName";

    @BeforeEach
    void setUp() {
        patientServiceMap = new PatientServiceMap();
        patientServiceMap.save(Patient.builder().id(patientId).lastName(lastName).build() );
    }

    @Test
    void findAll() {
        Set<Patient> patients = patientServiceMap.findAll();

        assertEquals(patientId,patients.size());
    }

    @Test
    void deleteById() {
        patientServiceMap.deleteById(patientId);
        assertEquals(0,patientServiceMap.findAll().size());
    }

    @Test
    void delete() {
        patientServiceMap.delete(patientServiceMap.findById(patientId));
        assertEquals(0,patientServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Patient patient =Patient.builder().id(2L).build();
        Patient savedPatient = patientServiceMap.save(patient);
        assertEquals(2L,savedPatient .getId());
        assertNotNull(patient);
    }

    @Test
    void saveWithoutId() {
        Patient savedPatient = patientServiceMap.save(Patient.builder().build());
        assertNotNull(savedPatient);
        assertNotNull(savedPatient.getId());
    }

    @Test
    void findById() {
        Patient patient =  patientServiceMap.findById(patientId);
        assertEquals(patientId,patient.getId());
    }

    @Test
    void findByLastName() {
        Patient patient = patientServiceMap.findByLastName(lastName);
        assertEquals(lastName,patient.getLastName());
        assertNotNull(patient);
    }

    @Test
    void findByLastNameNotFound() {
        Patient patient = patientServiceMap.findByLastName("lastName");
        assertNull(patient);
    }
}