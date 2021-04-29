package dentalclinic.services.springdatajpa;

import dentalclinic.model.Patient;
import dentalclinic.repositories.PatientRepository;
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
class PatientSDJPAServiceTest {
    public static final String LAST_NAME = "lastName";
    @Mock
    PatientRepository patientRepository;
    @InjectMocks
    PatientSDJPAService service;
    Patient returnedPatient;

    @BeforeEach
    void setUp() {
        returnedPatient = Patient.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Patient> returnedPatients = new HashSet<>();
        returnedPatients.add(Patient.builder().id(1L).build());
        returnedPatients.add(Patient.builder().id(2L).build());

        when(patientRepository.findAll()).thenReturn(returnedPatients);

        Set<Patient> patients = service.findAll();

        assertNotNull(patients);
        assertEquals(2, patients.size());
    }

    @Test
    void findById() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(returnedPatient));

        Patient patient = service.findById(1L);

        assertNotNull(patient);
        assertEquals(1, patient.getId());
    }

    @Test
    void findByIdNotFound() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Patient patient = service.findById(1L);

        assertNull(patient);
    }

    @Test
    void save() {
        Patient patientToSave = Patient.builder().id(1L).build();

        when(patientRepository.save(any())).thenReturn(returnedPatient);

        Patient savedPatient = service.save(patientToSave);

        assertNotNull(savedPatient);
        assertEquals(1, savedPatient.getId());
        verify(patientRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedPatient);
        verify(patientRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(patientRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        Patient returnedPatient = Patient.builder().id(1L).lastName(LAST_NAME).build();

        when(patientRepository.findByLastName(any())).thenReturn(returnedPatient);

        Patient patient = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, patient.getLastName());

    }
}