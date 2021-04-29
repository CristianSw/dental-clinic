package dentalclinic.services.springdatajpa;

import dentalclinic.model.Patient;
import dentalclinic.model.Visit;
import dentalclinic.repositories.PatientRepository;
import dentalclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJPAServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJPAService service;
    Visit returnedVisit;



    @BeforeEach
    void setUp() {
        Patient patient = Patient.builder().id(1L).build();
        returnedVisit = Visit.builder().id(1L).patient(patient).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnedVisits = new HashSet<>();
        returnedVisits.add(Visit.builder().id(1L).build());
        returnedVisits.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(returnedVisits);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2,visits.size());
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnedVisit));

        Visit visit = service.findById(1L);

        assertNotNull(visit);
        assertEquals(1,visit.getId());
        verify(visitRepository).findById(anyLong());
    }
    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(1L);

        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).patient(Patient.builder().id(1L).build()).build();

        when(visitRepository.save(any())).thenReturn(visitToSave);

        Visit visit = service.save(visitToSave);

        assertNotNull(visit);
        assertEquals(1,visit.getId());
        assertEquals(1,visit.getPatient().getId());
        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedVisit);

        assertEquals(0,service.findAll().size());
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        assertEquals(0,service.findAll().size());
        verify(visitRepository).deleteById(anyLong());
    }
}