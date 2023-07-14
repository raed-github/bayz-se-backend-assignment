package com.bayzdelivery.dto;

import java.time.Instant;

/**
 * A request class that consists of the delivery request object accepted by controller.
 * @author Raed
 *
 */
public class DeliveryRequest {
    private Instant startTime;
    private Instant endTime;
    private Long distance;
    private Double price;
    private Long deliveryManId;
    private Long customerId;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getDeliveryManId() {
        return deliveryManId;
    }
    public void setDeliveryManId(Long deliveryManId) {
        this.deliveryManId = deliveryManId;
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
    private Long orderId;
}
