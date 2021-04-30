package dentalclinic.controllers;

import com.sun.istack.NotNull;
import dentalclinic.model.Patient;
import dentalclinic.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientsControllerTest {

    @Mock
    PatientService patientService;

    @InjectMocks
    PatientsController patientsController;
    Set<Patient> patients;
    Set<Patient> patient;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        patients = new HashSet<>();
        patient = new HashSet<>();
        patients.add(Patient.builder().id(1L).build());
        patients.add(Patient.builder().id(2L).build());
        patient.add(Patient.builder().id(1L).build());

        mockMvc = MockMvcBuilders.standaloneSetup( patientsController).build();
    }

    @Test
    void findPatients() throws Exception {
        mockMvc.perform(get("/patients/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/findPatients"));
    }
    @Test
    void displayPatient() throws Exception {
        when(patientService.findById(anyLong())).thenReturn(Patient.builder().id(1L).build());

        mockMvc.perform(get("/patients/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/patientDetails"))
                .andExpect(model().attribute("patient", hasProperty("id",is(1L))));
    }


    @Test
    void processFindFormReturnMany() throws Exception {
        when(patientService.findByLastNameLike(anyString())).thenReturn(Arrays.asList(Patient.builder().id(1L).build(),
                Patient.builder().id(2L).build()));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/patientsList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(patientService.findByLastNameLike(anyString())).thenReturn(Arrays.asList(Patient.builder().id(1L).build()));


        mockMvc.perform(get("/patients"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients/1"));
    }
}