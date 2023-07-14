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
        PersonRequest personRequest = new PersonRequest();
        personRequest.setName("John");
        personRequest.setEmail("John@mydomain.com");
        personRequest.setRegistrationNumber("123456");
        personRequest.setType(ApplicationConstants.DELIVERY_MEN);

        ResponseEntity<PersonResponse> personResponse =
                template.postForEntity(PEOPLE_API, personRequest,
                        PersonResponse.class);

        assertThat(personResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(personResponse.getBody().getEmail()).isEqualTo(personRequest.getEmail());
        assertThat(personResponse.getBody().getName()).isEqualTo(personRequest.getName());
        assertThat(personResponse.getBody().getType()).isEqualTo(personRequest.getType());
        assertThat(personResponse.getBody().getRegistrationNumber()).isEqualTo(personRequest.getRegistrationNumber());
        assert(personResponse.getBody().getId()!=null);
    }

    @Test
    public void testShouldGetPersonByID(){
        Person person = new Person();
        person.setName("John");
        person.setEmail("john@mydomain.com");
        person.setRegistrationNumber("123456");
        person.setType("DELIVERY_MEN");

        Person savedPerson = personRepository.save(person);

        ResponseEntity<PersonResponse> personResponse =
                template.getForEntity(PEOPLE_API+"/"+savedPerson.getId(),
                        PersonResponse.class);

        assertThat(personResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResponse.getBody().getEmail()).isEqualTo(person.getEmail());
        assertThat(personResponse.getBody().getName()).isEqualTo(person.getName());
        assertThat(personResponse.getBody().getType()).isEqualTo(person.getType());
        assertThat(personResponse.getBody().getRegistrationNumber()).isEqualTo(person.getRegistrationNumber());
        assert(personResponse.getBody().getId()!=null);
    }

    @Test
    public void testShouldGetAllPeople(){
        Person person = new Person();
        person.setName("John");
        person.setEmail("john@mydomain.com");
        person.setRegistrationNumber("123456");
        person.setType("DELIVERY_MEN");

        Person savedPerson = personRepository.save(person);

        List personResponseList =
                template.getForObject(PEOPLE_API,
                        List.class);

        assertThat(personResponseList.size()).isGreaterThan(0);

    }

    @Test
    public void testDeliveryManShouldDeliver() {
        Person deliveryMan = new Person();
        deliveryMan.setName("John");
        deliveryMan.setEmail("John@mydomain.com");
        deliveryMan.setRegistrationNumber("123456");
        deliveryMan.setType(ApplicationConstants.DELIVERY_MEN);
        deliveryMan = personRepository.save(deliveryMan);

        Person customer = new Person();
        customer.setName("Peter");
        customer.setEmail("peter@mydomain.com");
        customer.setRegistrationNumber("123456");
        customer.setType(ApplicationConstants.CUSTOMER);
        customer = personRepository.save(customer);

        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setStartTime(Instant.parse("2022-01-01T09:00:00Z"));
        deliveryRequest.setEndTime(Instant.parse("2022-02-01T09:00:00Z"));
        deliveryRequest.setDistance(10L);
        deliveryRequest.setPrice(100.0);
        deliveryRequest.setOrderId(1234L);
        deliveryRequest.setDeliveryManId(1L);
        deliveryRequest.setCustomerId(2L);

        ResponseEntity<DeliveryResponse> deliverResponse =
                template.postForEntity(DELIVERY_API, deliveryRequest,
                        DeliveryResponse.class);

        assertThat(deliverResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(deliverResponse.getBody().getDistance()).isEqualTo(deliveryRequest.getDistance());
        assertThat(deliverResponse.getBody().getPrice()).isEqualTo(deliveryRequest.getPrice());
        assertThat(deliverResponse.getBody().getOrderId()).isEqualTo(deliveryRequest.getOrderId());
        assert(deliverResponse.getBody().getDeliveryId()!=null);
        assert(deliverResponse.getBody().getCommission()!=null);

    }

    @Test
    public void testGetDeliveryById(){
        Person deliveryMan = new Person();
        deliveryMan.setName("John");
        deliveryMan.setEmail("John@mydomain.com");
        deliveryMan.setRegistrationNumber("123456");
        deliveryMan.setType(ApplicationConstants.DELIVERY_MEN);
        deliveryMan = personRepository.save(deliveryMan);

        Person customer = new Person();
        customer.setName("Peter");
        customer.setEmail("peter@mydomain.com");
        customer.setRegistrationNumber("123456");
        customer.setType(ApplicationConstants.CUSTOMER);
        customer = personRepository.save(customer);

        Delivery delivery = new Delivery();
        delivery.setStartTime(Instant.parse("2022-01-01T09:00:00Z"));
        delivery.setEndTime(Instant.parse("2022-02-01T09:00:00Z"));
        delivery.setDistance(10L);
        delivery.setPrice(100.0);
        delivery.setComission(10.0);
        delivery.setOrderId(1234L);
        delivery.setDeliveryMan(deliveryMan);
        delivery.setCustomer(customer);

        delivery = deliveryRepository.save(delivery);

        ResponseEntity<DeliveryResponse> deliveryResponse =
                template.getForEntity(DELIVERY_API+"/"+delivery.getId(),
                        DeliveryResponse.class);

        assertThat(deliveryResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deliveryResponse.getBody().getOrderId()).isEqualTo(delivery.getOrderId());
        assertThat(deliveryResponse.getBody().getPrice()).isEqualTo(delivery.getPrice());
        assertThat(deliveryResponse.getBody().getDistance()).isEqualTo(delivery.getDistance());
        assertThat(deliveryResponse.getBody().getStartTime()).isEqualTo(delivery.getStartTime());
        assertThat(deliveryResponse.getBody().getEndTime()).isEqualTo(delivery.getEndTime());
        assertThat(deliveryResponse.getBody().getCommission()).isEqualTo(delivery.getComission());
        assert(deliveryResponse.getBody().getDeliveryId()!=null);

    }

    @Test
    public void testGetTopDeliveryMen(){
        Person deliveryMan = new Person();
        deliveryMan.setName("John");
        deliveryMan.setEmail("John@mydomain.com");
        deliveryMan.setRegistrationNumber("123456");
        deliveryMan.setType(ApplicationConstants.DELIVERY_MEN);
        deliveryMan = personRepository.save(deliveryMan);

        Person customer = new Person();
        customer.setName("Peter");
        customer.setEmail("peter@mydomain.com");
        customer.setRegistrationNumber("123456");
        customer.setType(ApplicationConstants.CUSTOMER);
        customer = personRepository.save(customer);

        Delivery delivery = new Delivery();
        delivery.setStartTime(Instant.parse("2022-01-01T09:00:00Z"));
        delivery.setEndTime(Instant.parse("2022-02-01T09:00:00Z"));
        delivery.setDistance(10L);
        delivery.setPrice(100.0);
        delivery.setOrderId(1234L);
        delivery.setDeliveryMan(deliveryMan);
        delivery.setCustomer(customer);

        delivery = deliveryRepository.save(delivery);

        ResponseEntity<DeliveryResponse> deliveryResponse =
                template.getForEntity("/api/deliveries/top-earners?startDate=2021-01-01T09:00:00Z&endDate=2025-01-01T09:00:00Z",
                        DeliveryResponse.class);

        assertThat(deliveryResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deliveryResponse.getBody().getAverageCommission()).isNotNull();
    }
}
