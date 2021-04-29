package dentalclinic.controllers;

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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
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

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        patients = new HashSet<>();
        patients.add(Patient.builder().id(1L).build());
        patients.add(Patient.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup( patientsController).build();
    }

    @Test
    void index() throws Exception {
        when(patientService.findAll()).thenReturn(patients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/index"))
                .andExpect(model().attribute("patients",hasSize(2)));
    }

    @Test
    void listOfPatientsByPacient() throws Exception {
        when(patientService.findAll()).thenReturn(patients);

        mockMvc.perform(get("/pacient"))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/index"))
                .andExpect(model().attribute("patients",hasSize(2)));
    }
    @Test
    void listOfPatientsByIndex() throws Exception {
        when(patientService.findAll()).thenReturn(patients);

        mockMvc.perform(get("/patients/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/index"))
                .andExpect(model().attribute("patients",hasSize(2)));
    }

    @Test
    void findPatients() throws Exception {
        mockMvc.perform(get("/patients/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("NotImplemented"));
    }
}