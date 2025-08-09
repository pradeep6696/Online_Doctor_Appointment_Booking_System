package com.example.doctorconsultation.repository;

import com.example.doctorconsultation.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}