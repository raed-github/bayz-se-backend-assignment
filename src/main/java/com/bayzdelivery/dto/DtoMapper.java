package com.bayzdelivery.dto;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;

/**
 * DtoMapper class which is responsible for Dto pattern mapping between models and dto objects.
 * @author Raed
 *
 */
public class DtoMapper {

    /**
     * Converts delivery request to delivery model.
     * @author Raed
     *
     */
    public static Delivery toDelivery(DeliveryRequest deliveryRequest){

        Delivery delivery = new Delivery();
        Person deliveryMan = new Person();
        deliveryMan.setId(deliveryRequest.getDeliveryManId());

        Person customer = new Person();
        customer.setId(deliveryRequest.getCustomerId());

        delivery.setDeliveryMan(deliveryMan);
        delivery.setCustomer(customer);
        delivery.setPrice(deliveryRequest.getPrice());
        delivery.setDistance(deliveryRequest.getDistance());
        delivery.setStartTime(deliveryRequest.getStartTime());
        delivery.setEndTime(deliveryRequest.getEndTime());
        delivery.setOrderId(deliveryRequest.getOrderId());

        return delivery;
    }

    /**
     * Converts delivery model to delivery response.
     * @author Raed
     *
     */
    public static DeliveryResponse toDeliveryResponse(Delivery delivery){
        DeliveryResponse deliveryResponse = new DeliveryResponse(delivery.getId(),delivery.getStartTime(),delivery.getEndTime(),delivery.getDistance(),delivery.getPrice(),delivery.getOrderId(), delivery.getComission());
        deliveryResponse.setDeliveryMan(toPersonResponse(delivery.getDeliveryMan()));
        deliveryResponse.setCustomer(toPersonResponse(delivery.getCustomer()));
        return deliveryResponse;
    }

    /**
     * Converts person request to person model.
     * @author Raed
     *
     */
    public static Person toPerson(PersonRequest personRequest){
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setEmail(personRequest.getEmail());
        person.setRegistrationNumber(personRequest.getRegistrationNumber());
        person.setType(personRequest.getType());
        return person;
    }

    /**
     * Converts person model to person response.
     * @author Raed
     *
     */
    public static PersonResponse toPersonResponse(Person person){
        return new PersonResponse(person.getId(),person.getName(),person.getEmail(),person.getRegistrationNumber(),person.getType());
    }

}
