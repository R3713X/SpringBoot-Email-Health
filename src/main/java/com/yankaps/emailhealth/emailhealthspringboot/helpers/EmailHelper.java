package com.yankaps.emailhealth.emailhealthspringboot.helpers;

import com.yankaps.emailhealth.emailhealthspringboot.models.Email;
import org.springframework.util.StringUtils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yannis kapsalas
 *
 * This class will be sesponcible for preparing validating and sending emails.
 */
public class EmailHelper {

  private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  private static final String USERNAME = "springbootemailorange2020@gmail.com";
  private static final String PASSWORD = "orange202020";

  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);


  /**
   * @param email
   * Prepares and sends email.
   * The only public method of this class
   */
  public static boolean handleEmail(Email email) throws MessagingException {
    if (isValidAddress(email.getEmail())) {
      MimeMessage message = prepareEmail(email);
      return attemptSendEmail(message);
    }
    return false;
  }

  /**
   * Prepares MimeMessage
   * @param email
   */
  protected static MimeMessage prepareEmail(Email email) throws MessagingException {
    Properties properties = System.getProperties();

    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
      }
    });
    MimeMessage mimeMessage = new MimeMessage(session);
    if (!StringUtils.isEmpty(email.getEmail())) {
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmail()));
    }
    mimeMessage.setText(email.getContent());
    mimeMessage.setSubject(email.getSubject());
    return mimeMessage;
  }

  /**
   * Checks if the email address is as specified on EMAIL_REGEX
   * @param emailAddressInput
   * @return
   */
  protected static boolean isValidAddress(String emailAddressInput) {
    Matcher matcher = EMAIL_PATTERN.matcher(emailAddressInput);
    return matcher.find();
  }

  /***
   *  Attempts to send email.
   * @param email
   * @return
   */
  protected static boolean attemptSendEmail(MimeMessage email) {
    try {
      Transport.send(email);
      return true;
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return false;
  }

}
