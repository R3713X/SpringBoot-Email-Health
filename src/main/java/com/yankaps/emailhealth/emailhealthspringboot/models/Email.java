package com.yankaps.emailhealth.emailhealthspringboot.models;

/**
 * @author Yannis Kapsalas
 */
public class Email {
  private String email;
  private String subject;
  private String content;

  public Email(String email, String subject, String content) {
    this.email = email;
    this.subject = subject;
    this.content = content;
  }

  public String getEmail() {
    return email;
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }
}
