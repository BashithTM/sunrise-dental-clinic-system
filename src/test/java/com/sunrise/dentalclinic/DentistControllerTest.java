package com.sunrise.dentalclinic;

import com.sunrise.dentalclinic.model.*;
import com.sunrise.dentalclinic.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class DentistControllerTest {
    @Autowired MockMvc mvc;
    @Autowired DentistRepository dentists;
    @Autowired PatientRepository patients;
    @Autowired TreatmentRepository treatments;
    @Autowired AppointmentRepository appointments;

    @Test
    void dentistListShowsDeleteOption() throws Exception {
        Dentist dentist = saveDentist("Dr Delete Option");
        mvc.perform(get("/dentists").with(user("staff").roles("STAFF")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/dentists/" + dentist.getId() + "/delete")))
                .andExpect(content().string(containsString("Delete this dentist?")));
    }

    @Test
    void deletesDentistWithoutAppointments() throws Exception {
        Dentist dentist = saveDentist("Dr Removable");
        mvc.perform(post("/dentists/{id}/delete", dentist.getId())
                        .with(user("staff").roles("STAFF")).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dentists"))
                .andExpect(flash().attribute("success", "Dentist deleted successfully"));
        assertFalse(dentists.existsById(dentist.getId()));
    }

    @Test
    void preventsDeletingDentistWithAppointments() throws Exception {
        Dentist dentist = saveDentist("Dr Protected");
        Patient patient = new Patient();
        patient.setName("Protected Patient");
        patient.setAddress("Colombo");
        patient.setContactNumber("0771234567");
        patient = patients.save(patient);
        Treatment treatment = new Treatment();
        treatment.setTreatmentName("Protected Treatment");
        treatment.setTreatmentCost(new BigDecimal("5000.00"));
        treatment.setActive(true);
        treatment = treatments.save(treatment);
        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber("APT-DENTIST-PROTECTED");
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setTreatment(treatment);
        appointment.setAppointmentDate(LocalDate.now().plusDays(1));
        appointment.setAppointmentTime(LocalTime.of(9, 0));
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointments.save(appointment);

        mvc.perform(post("/dentists/{id}/delete", dentist.getId())
                        .with(user("staff").roles("STAFF")).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dentists"))
                .andExpect(flash().attribute("error", containsString("cannot be deleted")));
        assertTrue(dentists.existsById(dentist.getId()));
    }

    private Dentist saveDentist(String name) {
        Dentist dentist = new Dentist();
        dentist.setName(name);
        dentist.setSpecialization("General Dentistry");
        dentist.setActive(true);
        return dentists.save(dentist);
    }
}
