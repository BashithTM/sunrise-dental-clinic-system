# Sunrise Dental Clinic Test Plan

Actual results below reflect the successful Maven test execution on 2026-09-03 using JDK 22 with Java 21 release compatibility and the H2 `test` profile.

| Test ID | Requirement | Type | Test data | Expected result | Automated test class/method | Actual result | Status |
|---|---|---|---|---|---|---|---|
| BILL-01 | Correct bill total | Unit | 2500.50 + 1000.00 | 3500.50, scale 2 | `BillingServiceTest.calculatesBillTotalWithCurrencyScale` | 3500.50 | Pass |
| BILL-02 | No duplicate bill | Unit/Mockito | Existing bill for APT-1 | Business exception | `BillingServiceTest.preventsDuplicateBill` | Exception raised | Pass |
| APT-01 | Prevent dentist double-booking | Unit/Mockito | Same dentist/date/time, active status | Business exception | `AppointmentServiceTest.preventsDoubleBooking` | Exception raised | Pass |
| APT-02 | Cancelled slot may be reused | Unit/Mockito | No non-cancelled conflict | Appointment accepted | `AppointmentServiceTest.cancelledAppointmentDoesNotBlockSlot` | Accepted | Pass |
| APT-03 | Reject past appointment dates | Unit | Past date against fixed clock | Business exception | `AppointmentServiceTest.rejectsPastDate` | Exception raised | Pass |
| APT-04 | Generate appointment number | Unit | Fixed date 2026-09-03 | `APT-20260903-#####` | `AppointmentServiceTest.generatesAppointmentNumber` | Matching number generated | Pass |
| PAT-01 | Patient and phone validation | Unit | Blank fields, `abc` phone | Three violations | `PatientValidationTest.rejectsBlankFieldsAndInvalidPhone` | Three violations | Pass |
| SEC-01 | Anonymous pages protected | Integration | GET `/dashboard` | Redirect | `SecurityAndApiTest.protectedPageRedirectsAnonymousToLogin` | Redirected | Pass |
| SEC-02 | Valid staff login | Integration | BCrypt-backed test user | Authenticated | `SecurityAndApiTest.validLoginCanAccessDashboard` | Authenticated | Pass |
| REP-01 | Patient repository search | JPA integration | Nimal / 077 contact | Found by either field | `RepositoryQueryTest.patientSearchMatchesNameOrContact` | Found | Pass |
| REP-02 | Appointment date query | JPA integration | Empty database | Empty result | `RepositoryQueryTest.dateQueryReturnsNoRowsForEmptyDatabase` | Empty | Pass |
| API-01 | Safe REST validation errors | Integration | Empty JSON | HTTP 400 | `SecurityAndApiTest.restValidationReturnsSafeBadRequest` | HTTP 400 | Pass |
| API-02 | REST appointment creation | Integration | Valid future slot | HTTP 201 with number | `SecurityAndApiTest.createsAppointmentThroughRest` | HTTP 201 | Pass |
| APP-01 | Application context startup | Integration | H2 test profile | Context loads | `DentalClinicSystemApplicationTests.contextLoads` | Started | Pass |

Suite result: **14 tests run, 0 failures, 0 errors, 0 skipped**.
