package com.sunrise.dentalclinic.dto;
import jakarta.validation.Validation;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class PatientValidationTest{@Test void rejectsBlankFieldsAndInvalidPhone(){try(var factory=Validation.buildDefaultValidatorFactory()){var violations=factory.getValidator().validate(new PatientRequest("","","abc"));assertEquals(3,violations.size());}}}
