package com.yankaps.emailhealth.emailhealthspringboot.spring;

import com.yankaps.emailhealth.emailhealthspringboot.EmailHealthSpringbootApplication;
import com.yankaps.emailhealth.emailhealthspringboot.helpers.EmailHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.yankaps.emailhealth.emailhealthspringboot.spring.SpringController.EMAIL_ERROR;
import static com.yankaps.emailhealth.emailhealthspringboot.spring.SpringController.EMAIL_SEND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpringControllerTest {
  private MockMvc mockMvcApp;
  private MockMvc mockMvpSpring;

  @Before
  public void setup() {
    this.mockMvcApp = MockMvcBuilders.standaloneSetup(EmailHealthSpringbootApplication.class).build();
    this.mockMvpSpring = MockMvcBuilders.standaloneSetup(SpringController.class).build();
  }


  @Test
  public void testApplicationController() throws Exception {
    mockMvcApp.perform(get("/"))
            .andExpect(status().isNotFound()); // no mapping yet

  }

  @Test
  public void testEmailProcess() throws Exception {
    mockMvpSpring.perform(post("/email")
            .param("email", "walala@onetwo.com")
            .param("subject", "Hi"))
            .andExpect(status().isOk())
            .andExpect(content().string(EMAIL_SEND));

    mockMvpSpring.perform(post("/email")
            .param("email", "walala@onetwo.11")
            .param("subject", "Hi"))
            .andExpect(status().isOk())
            .andExpect(content().string(EMAIL_ERROR));


    mockMvpSpring.perform(post("/email")
            .param("email", "walala@onetwo.11")
            .param("subject", "Hi"))
            .andExpect(status().isOk())
            .andExpect(content().string(EMAIL_ERROR));


    mockMvpSpring.perform(post("/email")
            .param("email", "walala@onetwo.11"))
            .andExpect(status().isOk())
            .andExpect(content().string(EMAIL_ERROR));

    mockMvpSpring.perform(post("/email")
            .param("email", "walala@onetwo.com"))
            .andExpect(status().isOk())
            .andExpect(content().string(EMAIL_SEND));

  }

  @Test
  public void testHealth() throws Exception {
    mockMvpSpring.perform(get("/health"))
            .andExpect(status().isOk());
  }

}
