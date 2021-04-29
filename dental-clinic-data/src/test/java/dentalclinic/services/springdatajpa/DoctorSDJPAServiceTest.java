package dentalclinic.services.springdatajpa;

import dentalclinic.model.Doctor;
import dentalclinic.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class DoctorSDJPAServiceTest {
    @Mock
    DoctorRepository doctorRepository;
    @InjectMocks
    DoctorSDJPAService service;
    Doctor returnedDoctor;
    final String LAST_NAME = "smth";

    @BeforeEach
    void setUp() {
        returnedDoctor = Doctor.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Doctor> returnedDoctors = new HashSet<>();
        returnedDoctors.add(Doctor.builder().id(1L).build());
        returnedDoctors.add(Doctor.builder().id(2L).build());

        when(doctorRepository.findAll()).thenReturn(returnedDoctors);

        Set<Doctor> doctors = service.findAll();

        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        verify(doctorRepository).findAll();
    }

    @Test
    void findById() {
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(returnedDoctor));

        Doctor doctor = service.findById(1L);

        assertNotNull(doctor);
        assertEquals(1,doctor.getId());
        verify(doctorRepository).findById(anyLong());
    }

    @Test
    void save() {
        Doctor savedDoctor = Doctor.builder().id(1L).build();

        when(doctorRepository.save(any())).thenReturn(savedDoctor);

        Doctor doctor = service.save(savedDoctor);

        assertNotNull(doctor);
        assertEquals(1,doctor.getId());
        verify(doctorRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedDoctor);
        verify(doctorRepository).delete(any());
        assertEquals(0,service.findAll().size());

    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(doctorRepository).deleteById(anyLong());
        assertEquals(0,service.findAll().size());
    }

    @Test
    void findByLastName() {
        when(doctorRepository.findByLastName(any())).thenReturn(returnedDoctor);

        Doctor doctor = service.findByLastName(LAST_NAME);

        assertNotNull(doctor);
        assertEquals(LAST_NAME,doctor.getLastName());
        assertEquals(1L,doctor.getId());
        verify(doctorRepository).findByLastName(any());
    }
}