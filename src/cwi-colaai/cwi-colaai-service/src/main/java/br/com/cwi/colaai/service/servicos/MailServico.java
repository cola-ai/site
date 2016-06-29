
package br.com.cwi.colaai.service.servicos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author alycio.neto
 */
@Service
public class MailServico {

    private static final Logger LOGGER = Logger.getLogger(MailServico.class.getName());
    private static final String DE_EMAIL = "colaaicarona@gmail.com";

    @Autowired
    JavaMailSenderImpl mailSender;

    public void enviarEmail(String para, String titulo, String mensagem) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setSubject(titulo);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);            
            helper.setTo(para);
            helper.setText(mensagem, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
