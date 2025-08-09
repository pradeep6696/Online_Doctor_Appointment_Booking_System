package com.example.doctorconsultation.controller;

import com.example.doctorconsultation.model.Appointment;
import com.example.doctorconsultation.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/form")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointmentForm"; // Name of the Thymeleaf template
    }

    @PostMapping("/create")
    public String createAppointment(@RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("doctor") String doctor,
                                    @RequestParam("hospital") String hospital,
                                    @RequestParam("appointmentDate") String appointmentDateStr,
                                    @RequestParam("appointmentTime") String appointmentTimeStr,
                                    Model model) {

        // Parse the date and time strings
        LocalDate appointmentDate = LocalDate.parse(appointmentDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime appointmentTime = LocalTime.parse(appointmentTimeStr, DateTimeFormatter.ISO_LOCAL_TIME);

        // Create the Appointment object
        Appointment appointment = new Appointment(name, email, doctor, hospital, appointmentDate, appointmentTime);

        // Save the appointment to the database
        appointmentRepository.save(appointment);

        // Add a success message to the model
        model.addAttribute("message", "Appointment created successfully!");

        // Redirect to a confirmation page or back to the form
        return "success"; // Redirect to a success template
    }
}