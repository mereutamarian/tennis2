package mereuta.marian.tennis01.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component

public class MailMetier implements MailMetierInterface {

    @Autowired
    MailSender mailSender;



    private String destinataire="mereuta.marian@gmail.com";

    @Override
    public boolean sendSimpleMail(String expediteur, String sujet,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(expediteur);
        mailMessage.setSubject(sujet);
        mailMessage.setText(message);
        mailMessage.setTo(destinataire); // if you use Gmail do not forget to put your personal address

        try {
            mailSender.send(mailMessage);
            return true;
        } catch (MailException e) {

            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void modifierAdresseMail(String email) {

        destinataire=email;
    }
}
