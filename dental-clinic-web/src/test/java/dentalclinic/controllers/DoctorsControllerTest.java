package dentalclinic.controllers;

import dentalclinic.model.Doctor;
import dentalclinic.services.DoctorService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DoctorsControllerTest {
    @Mock
    DoctorService doctorService;
    @InjectMocks
    DoctorsController doctorsController;
    Set<Doctor> doctors;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        doctors = new HashSet<>();
        doctors.add(Doctor.builder().id(1L).build());
        doctors.add(Doctor.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(doctorsController).build();
    }

    @Test
    void findDoctorsHTML() throws Exception {
        when(doctorService.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/doctors/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("/doctors/index"))
                .andExpect(model().attribute("doctors",hasSize(2)));
    }
    @Test
    void findDocsHTML() throws Exception {
        when(doctorService.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/docs.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("/doctors/index"))
                .andExpect(model().attribute("doctors",hasSize(2)));
    }
    @Test
    void findDoctors() throws Exception {
        when(doctorService.findAll()).thenReturn(doctors);

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(view().name("/doctors/index"))
                .andExpect(model().attribute("doctors",hasSize(2)));
    }
}