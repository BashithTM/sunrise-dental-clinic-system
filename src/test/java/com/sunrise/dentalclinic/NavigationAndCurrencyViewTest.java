package com.sunrise.dentalclinic;

import com.sunrise.dentalclinic.model.Treatment;
import com.sunrise.dentalclinic.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class NavigationAndCurrencyViewTest {
    @Autowired MockMvc mvc;
    @Autowired TreatmentRepository treatments;

    @Test
    void everySidebarDestinationLoadsForStaff() throws Exception {
        for (String path : new String[]{"/dashboard", "/patients", "/dentists", "/treatments",
                "/appointments", "/bills/new", "/reports", "/help"}) {
            mvc.perform(get(path).with(user("staff").roles("STAFF")))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void navigationUsesLocalAccessibleSvgIcons() throws Exception {
        mvc.perform(get("/dashboard").with(user("staff").roles("STAFF")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/images/module-icons.svg#dashboard")))
                .andExpect(content().string(containsString("/images/module-icons.svg#billing")))
                .andExpect(content().string(containsString("aria-hidden=\"true\"")))
                .andExpect(content().string(not(containsString("＄"))));

        mvc.perform(get("/images/module-icons.svg").with(user("staff").roles("STAFF")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id=\"billing\"")));
    }

    @Test
    void treatmentPricesUseGroupedSriLankanCurrency() throws Exception {
        Treatment treatment = new Treatment();
        treatment.setTreatmentName("Currency display test");
        treatment.setTreatmentCost(new BigDecimal("5000.00"));
        treatment.setActive(true);
        treatments.save(treatment);

        mvc.perform(get("/treatments").with(user("staff").roles("STAFF")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("LKR 5,000.00")));
    }
}
