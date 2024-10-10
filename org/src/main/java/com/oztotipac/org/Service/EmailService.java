package com.oztotipac.org.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendAppointmentEmail(List<String> emails, LocalDate appointmentDate) {
        for (String email : emails) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            try {
                helper.setSubject("cxccc");
                helper.setText("ssssssss " + appointmentDate.toString());
                helper.setTo(email);

                javaMailSender.send(message);
            } catch (MessagingException e) {
                // Maneja la excepción adecuadamente
                System.err.println("Error al enviar correo a " + email + ": " + e.getMessage());
            }
        }
    }
}
