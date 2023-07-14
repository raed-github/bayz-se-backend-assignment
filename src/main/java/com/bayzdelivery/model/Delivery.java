package com.bayzdelivery.model;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{
  private static final long serialVersionUID = 123765351514001L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @Column(name = "start_time")
  Instant startTime;

  @NotNull
  @Column(name = "end_time")
  Instant endTime;

  @NotNull
  @Column(name = "distance")
  Long distance;
  @NotNull
  @Column(name = "price")
  Double price;

  @Column(name = "comission")
  Double comission;

  @NotNull
  @Column(name="order_id")
  Long orderId;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
  Person deliveryMan;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  Person customer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getStartTime() {
    return startTime;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public Long getDistance() {
    return distance;
  }

  public void setDistance(Long distance) {
    this.distance = distance;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getComission() {
    return comission;
  }

  public void setComission(Double comission) {
    this.comission = comission;
  }

  public Person getDeliveryMan() {
    return deliveryMan;
  }

  public void setDeliveryMan(Person deliveryMan) {
    this.deliveryMan = deliveryMan;
  }

  public Person getCustomer() {
    return customer;
  }

  public void setCustomer(Person customer) {
    this.customer = customer;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

}
