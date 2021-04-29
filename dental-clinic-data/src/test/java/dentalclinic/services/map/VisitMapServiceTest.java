package dentalclinic.services.map;

import dentalclinic.model.Patient;
import dentalclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {
    VisitMapService visitMapService;
    PatientServiceMap patientServiceMap;
    final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        patientServiceMap = new PatientServiceMap();
        patientServiceMap.save(Patient.builder().id(1L).build());
        Patient patient = patientServiceMap.findById(1L);
        visitMapService = new VisitMapService();
        visitMapService.save(Visit.builder().id(visitId).patient(patient).build());
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitMapService.findAll();
        assertEquals(visitId,visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(visitId);
        assertEquals(0,visitMapService.findAll().size());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(visitId));
        assertEquals(0,visitMapService.findAll().size());
    }

    @Test
    void save() {
        Visit visit = Visit.builder().id(2L).patient(patientServiceMap.findById(1L)).build();
        Visit savedVisit = visitMapService.save(visit);
        assertEquals(2L,savedVisit.getId());
        assertNotNull(savedVisit);
        assertEquals(2,visitMapService.findAll().size());
    }

    @Test
    void findById() {
        Visit visit = visitMapService.findById(visitId);
        assertEquals(visitId,visit.getId());

    }
}