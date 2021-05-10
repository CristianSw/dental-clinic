package dentalclinic.bootstrap;

import dentalclinic.model.Doctor;
import dentalclinic.model.Patient;
import dentalclinic.model.Visit;
import dentalclinic.services.DoctorService;
import dentalclinic.services.PatientService;
import dentalclinic.services.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
public class DataLoader implements CommandLineRunner {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final VisitService visitService;

    public DataLoader(DoctorService doctorService, PatientService patientService, VisitService visitService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        Doctor doctor1 = new Doctor();
        doctor1.setCabinetNumber(4);
        doctor1.setFirstName("Josh");
        doctor1.setLastName("Deere");

        doctorService.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setCabinetNumber(6);
        doctor2.setFirstName("Cristian");
        doctor2.setLastName("Dolinta");

        doctorService.save(doctor2);

        System.out.println("Doctors are loaded !!!!!!");


        Patient patient1 = new Patient();
        patient1.setFirstName("Ion");
        patient1.setLastName("Grubii");
        patient1.setPhoneNumber("+37379654822");

        patientService.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Igor");
        patient2.setLastName("Budeanu");
        patient2.setPhoneNumber("+37379652352");

        patientService.save(patient2);

        System.out.println("Patients are loaded !!!!!!!");


        Visit visit1 = new Visit();
        visit1.setPatient(patient1);
        visit1.setLocalDate(LocalDate.now());
        visit1.setDescription("Some Description");
        visitService.save(visit1);



        Visit visit2 = new Visit();
        visit2.setPatient(patient2);
        visit2.setLocalDate(LocalDate.now());
        visit2.setDescription("Some Description 2");
        visitService.save(visit2);
        System.out.println("Visits are loaded !!! ");

    }
}
