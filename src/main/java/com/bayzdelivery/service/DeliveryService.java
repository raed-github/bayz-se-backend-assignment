package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;

import java.time.Instant;
import java.util.List;

public interface DeliveryService {

  public Delivery save(Delivery delivery);
  public Delivery findById(Long deliveryId);
  public List<Person> getTopDeliveryMenByCommission(Instant startDate, Instant endDate);
  public Iterable<Delivery> findDelayedDeliveries();
  public Double getAverageCommission();
}
