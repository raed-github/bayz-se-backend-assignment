package com.bayzdelivery.controller;

import com.bayzdelivery.dto.*;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bayzdelivery.service.DeliveryService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Delivery Controller class which will be exposed.
 * @author Raed
 *
 */
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @PostMapping
  public ResponseEntity<DeliveryResponse> createNewDelivery(@RequestBody DeliveryRequest deliveryRequest) {
    Delivery delivery = deliveryService.save(DtoMapper.toDelivery(deliveryRequest));
    return new ResponseEntity(DtoMapper.toDeliveryResponse(delivery),HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable(name="id",required=true)Long deliveryId){
    Delivery delivery = deliveryService.findById(deliveryId);
    if (delivery !=null)
      return new ResponseEntity(DtoMapper.toDeliveryResponse(delivery), HttpStatus.OK);
    return ResponseEntity.notFound().build();
  }

}
