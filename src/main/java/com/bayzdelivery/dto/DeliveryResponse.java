package com.bayzdelivery.dto;

import java.time.Instant;

/**
 * A response class that consists of the delivery response object that will be returned to client.
 * @author Raed
 *
 */
public class DeliveryResponse {
    private Long deliveryId;
    private Instant startTime;
    private Instant endTime;
    private Long distance;
    private Double price;
    private Long orderId;
    private Double commission;

    private PersonResponse deliveryMan;

    public PersonResponse getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(PersonResponse deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public PersonResponse getCustomer() {
        return customer;
    }

    public void setCustomer(PersonResponse customer) {
        this.customer = customer;
    }

    private PersonResponse customer;
    public DeliveryResponse(Long deliveryId, Instant startTime, Instant endTime, Long distance, Double price, Long orderId, Double commission) {
        this.deliveryId = deliveryId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.price = price;
        this.orderId = orderId;
        this.commission = commission;
        this.averageCommission = averageCommission;
    }

    private Double averageCommission;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAverageCommission() {
        return averageCommission;
    }

    public void setAverageCommission(Double averageCommission) {
        this.averageCommission = averageCommission;
    }
}
