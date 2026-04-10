package com.thekingmoss.application.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void enviarCodigo(String destino, String codigo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destino);
            helper.setFrom("TheKingMoss <noreply.thekingmoss@gmail.com>");
            helper.setSubject("Recuperación de cuenta - TheKingMoss");

            String html = cargarTemplate(codigo);

            helper.setText(html, true); // 🔥 TRUE = HTML

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar correo", e);
        }
    }

    private String cargarTemplate(String codigo) {
        try {
            InputStream inputStream = getClass()
                    .getResourceAsStream("/templates/email-codigo.html");

            String html = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            return html.replace("{{codigo}}", codigo);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando template HTML", e);
        }
    }
}
