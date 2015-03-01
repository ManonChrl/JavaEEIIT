/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Manon
 */
@Singleton
public class EmailBean {

    @Resource(lookup = "mail/finalProjectMail")
    private Session mailSession;
    private static final Logger LOG = Logger.getLogger(EmailBean.class.getName());

    /**
     * Send an email to the user who has registered
     *
     * @param to the destination of the email
     * @param subject the subject of the email
     * @param body the body of the email
     */
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            msg.setSentDate(new Date());
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
        } catch (AddressException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
