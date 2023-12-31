package com.bayzdelivery.controller;

import com.bayzdelivery.constants.ApplicationConstants;
import com.bayzdelivery.dto.PersonRequest;
import com.bayzdelivery.dto.PersonResponse;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

  MockMvc mockMvc;

  @Mock
  private PersonController personController;

  @Autowired
  private TestRestTemplate template;

  @Autowired
  PersonRepository personRepository;

  private static final String API_URI = "/api/people";

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }

  @Test
  public void testUserShouldBeRegistered() {
    PersonRequest personRequest = new PersonRequest();
    personRequest.setName("John");
    personRequest.setEmail("John@mydomain.com");
    personRequest.setRegistrationNumber("123456");
    personRequest.setType(ApplicationConstants.DELIVERY_MEN);

    ResponseEntity<PersonResponse> personResponse =
            template.postForEntity(API_URI, personRequest,
                    PersonResponse.class);

    assertThat(personResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(personResponse.getBody().getEmail()).isEqualTo(personRequest.getEmail());
    assertThat(personResponse.getBody().getName()).isEqualTo(personRequest.getName());
    assertThat(personResponse.getBody().getType()).isEqualTo(personRequest.getType());
    assertThat(personResponse.getBody().getRegistrationNumber()).isEqualTo(personRequest.getRegistrationNumber());
    assert(personResponse.getBody().getId()!=null);
  }

}
