package com.yankaps.emailhealth.emailhealthspringboot.spring;


import com.yankaps.emailhealth.emailhealthspringboot.helpers.EmailHelper;
import com.yankaps.emailhealth.emailhealthspringboot.helpers.SystemDataCache;
import com.yankaps.emailhealth.emailhealthspringboot.models.Email;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


/***
 * @author Yannis Kapsalas
 */
@SpringBootApplication
@RestController
public class SpringController {

  protected static final String EMAIL_SEND = "Email send";
  protected static final String EMAIL_ERROR = "Error: Email address is not correct";

  @RequestMapping("/health")
  public SystemDataCache healthReportRequest() {
    return SystemDataCache.getInstance();
  }

  /***
   * Attempts to send an email. Only the recipients email is essentially required for this process.
   * Subject and content can be left blank.
   *
   * @param subject
   * @param content
   * @param email
   * @return
   * @throws MessagingException
   */
  @RequestMapping(value = "/email", method = RequestMethod.POST)
  public String emailSubmission(@RequestParam(value = "subject", defaultValue = "") String subject,
                                @RequestParam(value = "content", defaultValue = "") String content,
                                @RequestParam(value = "email", defaultValue = "") String email) throws MessagingException {
    if (EmailHelper.handleEmail(new Email(email, subject, content))) {
      return EMAIL_SEND;
    }
    return EMAIL_ERROR;
  }

}