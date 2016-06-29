/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author alycio.neto
 */
@Service
public class MailServico {

    private static final Logger LOGGER = Logger.getLogger(MailServico.class.getName());

    @Autowired
    JavaMailSender mailSender;

    public void enviarEmail(String para, String titulo, String mensagem) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setSubject(titulo);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);            
            helper.setFrom("colaaicarona@gmail.com");
            helper.setTo(para);
            helper.setText(mensagem, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
