package dentalclinic.bootstrap;

import dentalclinic.model.Doctor;
import dentalclinic.model.Patient;
import dentalclinic.services.DoctorService;
import dentalclinic.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final DoctorService doctorService;
    private final PatientService patientService;

    public DataLoader(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;

        this.patientService = patientService;
    }

    @Override
    public void run(String... args) throws Exception {

        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setCabinetNumber(4);
        doctor1.setFirstName("Josh");
        doctor1.setLastName("Deere");

        doctorService.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setCabinetNumber(6);
        doctor2.setFirstName("Cristian");
        doctor2.setLastName("Dolinta");

        doctorService.save(doctor2);

        System.out.println("Doctors are loaded !!!!!!");


        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setFirstName("Ion");
        patient1.setLastName("Grubii");
        patient1.setPhoneNumber("+37379654822");

        patientService.save(patient1);

        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setFirstName("Igor");
        patient2.setLastName("Budeanu");
        patient2.setPhoneNumber("+37379652352");

        patientService.save(patient2);

        System.out.println("Patients are loaded !!!!!!!");
    }
}
