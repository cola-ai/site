/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Token;
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
    
        public void confirmarUsuario (String para, String titulo, Token token){
            //MimeMessage message = javaMailSenderImpl.createMimeMessage();
            MimeMessage message = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setSubject(titulo);
                helper.setTo(para);
                helper.setText(mensagemConfirmacaoEmail(token), true);

            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
            javaMailSender.send(message);
        }
        
        private String mensagemConfirmacaoEmail(Token token){
            return new String("<h2> Ola "+ token.getUsuario().getPessoa().getNome()+ "!</h2><br/>"
                    + "Caso não tenha efetuado cadastro em nosso site, peço que desconsidere esta mensagem<br/>"
                    + "Segue abaixo o link de confirmação da conta de e-mail"+ " <a href=localhost:9090/confirma?valor="
                    + token.getValor() + "/> <br/> Atenciosamente <br/> Equipe Cola ai!"            
            );
        }
    
}
