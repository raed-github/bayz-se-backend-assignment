package com.bayzdelivery.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bayzdelivery.constants.ApplicationConstants;
import com.bayzdelivery.exceptions.GlobalException;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Delivery service impl class that holds and process delivery business requests.
 * @author Raed
 *
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  PersonRepository personRepository;

  public Delivery save(Delivery delivery) {
    Long deliveryManId = delivery.getDeliveryMan().getId();
    Long customerId = delivery.getCustomer().getId();

    Person deliveryMan = personRepository.findById(deliveryManId)
            .orElseThrow(() -> new GlobalException(ApplicationConstants.DELIVERY_MAN_NOT_FOUND));
    Person customer = personRepository.findById(customerId)
            .orElseThrow(() -> new GlobalException(ApplicationConstants.CUSTOMER_NOT_FOUND));
    delivery.setDeliveryMan(deliveryMan);
    delivery.setCustomer(customer);
    delivery.setComission(this.calculateCommission(delivery));
    return deliveryRepository.save(delivery);
  }
  public Delivery findById(Long deliveryId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    if (optionalDelivery.isPresent()) {
      return optionalDelivery.get();
    }else return null;
  }
  public double calculateCommission(Delivery delivery) {
    double deliveryPrice = delivery.getPrice();
    double commission = deliveryPrice * 0.05 + delivery.getDistance() * 0.5;
    return commission;
  }

  /**
   * display top 3 delivery men who earn the most commission in given time interval,
   * show average commission as well
   * @param startDate
   * @param endDate
   * @return
   */
  @Override
  public List<Person> getTopDeliveryMenByCommission(Instant startDate, Instant endDate) {
    return deliveryRepository.findTop3ByCommissionBetweenOrderByCommissionDesc(startDate, endDate);
  }

  /**
   * a method that will be used to find delayed deliveries
   * @return
   */
  @Override
  public Iterable<Delivery> findDelayedDeliveries() {
    List<Delivery> delayedDeliveries = new ArrayList<>();
    return deliveryRepository.findAll();
  }

  /**
   * a method that will return the average commissions
   * @return
   */
  @Override
  public Double getAverageCommission() {
    return deliveryRepository.getAverageCommission();
  }

  public DeliveryRepository getDeliveryRepository() {
    return deliveryRepository;
  }

  public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
    this.deliveryRepository = deliveryRepository;
  }

  public PersonRepository getPersonRepository() {
    return personRepository;
  }

  public void setPersonRepository(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

}
