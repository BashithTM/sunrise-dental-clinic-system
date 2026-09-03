package com.sunrise.dentalclinic.dto;
import java.math.BigDecimal;
public record SummaryResponse(long todayAppointments,long upcomingAppointments,long totalPatients,long totalDentists,BigDecimal dailyRevenue) {}
