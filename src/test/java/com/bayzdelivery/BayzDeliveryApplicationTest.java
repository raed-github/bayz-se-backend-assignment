package com.bayzdelivery;


import com.bayzdelivery.constants.ApplicationConstants;
import com.bayzdelivery.dto.DeliveryRequest;
import com.bayzdelivery.dto.DeliveryResponse;
import com.bayzdelivery.dto.PersonRequest;
import com.bayzdelivery.dto.PersonResponse;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BayzDeliveryApplicationTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    private static final String PEOPLE_API = "/api/people";

    private static final String DELIVERY_API = "/api/deliveries";

    @Test
    public void testUserShouldBeRegistered() {
    }
}
