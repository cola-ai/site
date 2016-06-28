/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

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
    @Autowired
    JavaMailSender  javaMailSender;
    
        public void sendMail(String para, String titulo, String corpo){
        //MimeMessage message = javaMailSenderImpl.createMimeMessage();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(titulo);
            helper.setTo(para);
            helper.setText(corpo);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        javaMailSender.send(message);
    }
    
}
