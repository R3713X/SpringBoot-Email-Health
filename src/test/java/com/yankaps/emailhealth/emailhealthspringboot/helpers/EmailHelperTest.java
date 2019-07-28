package com.yankaps.emailhealth.emailhealthspringboot.helpers;

import com.yankaps.emailhealth.emailhealthspringboot.models.Email;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class EmailHelperTest {

  private Email validEmailWithAllFields;
  private Email invalidEmailWithAllFields;
  private Email emptyInvalidEmail;
  private Email justAddressEmail;


  @Before
  public void setup() throws MessagingException {
    validEmailWithAllFields = new Email("onetwo@one.gr","test","test");
    invalidEmailWithAllFields = new Email("onetwo@one.11","test","test");
    emptyInvalidEmail = new Email("","","");
    justAddressEmail = new Email("onetwo@one.gr", "", "");


  }

  @Test
  public void testisValidAddress() {
    assertTrue(EmailHelper.isValidAddress(validEmailWithAllFields.getEmail()));
    assertTrue(EmailHelper.isValidAddress(justAddressEmail.getEmail()));
    assertFalse(EmailHelper.isValidAddress(invalidEmailWithAllFields.getEmail()));
    assertFalse(EmailHelper.isValidAddress(emptyInvalidEmail.getEmail()));

  }

  @Test
  public void testPrepareEmail() throws MessagingException, IOException {

    MimeMessage validEmailWithAllFieldsMime = EmailHelper.prepareEmail(validEmailWithAllFields);
    assertEquals("test", validEmailWithAllFieldsMime.getSubject());
    assertEquals("test", validEmailWithAllFieldsMime.getContent());
    assertEquals("onetwo@one.gr", validEmailWithAllFieldsMime.getAllRecipients()[0].toString());

    MimeMessage invalidEmailWithAllFieldsMime = EmailHelper.prepareEmail(invalidEmailWithAllFields);
    assertEquals("test", invalidEmailWithAllFieldsMime.getSubject());
    assertEquals("test", invalidEmailWithAllFieldsMime.getContent());
    assertEquals("onetwo@one.11", invalidEmailWithAllFieldsMime.getAllRecipients()[0].toString());



    MimeMessage emptyInvalidEmailMime = EmailHelper.prepareEmail(emptyInvalidEmail);
    assertEquals("", emptyInvalidEmailMime.getSubject());
    assertEquals("", emptyInvalidEmailMime.getContent());
    assertNull(emptyInvalidEmailMime.getAllRecipients());


    MimeMessage justAddressEmailMime = EmailHelper.prepareEmail(justAddressEmail);
    assertEquals("", justAddressEmailMime.getSubject());
    assertEquals("", justAddressEmailMime.getContent());
    assertEquals("onetwo@one.gr",justAddressEmailMime.getAllRecipients()[0].toString());
  }

}