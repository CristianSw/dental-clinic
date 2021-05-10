package dentalclinic.controllers;

import dentalclinic.model.Patient;
import dentalclinic.model.Visit;
import dentalclinic.services.PatientService;
import dentalclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PatientService patientService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    Patient patient;
    Visit visit;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        patient = Patient.builder().id(1L).build();
        visit = Visit.builder().id(1L).patient(patient).build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get("/patients/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(post("/patients/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients/1"))
                .andExpect(model().attributeExists("visit"));
    }
}