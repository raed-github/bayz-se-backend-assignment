package com.bayzdelivery.jobs;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * A notifier class that checks for delayed deliveries.
 * @author Raed
 *
 */
@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    @Autowired
    private DeliveryService deliveryService;


     /**
     * A scheduled method that will check for delayed deliveries and notify support accordenly
     * @author Raed
     *
     */
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {
        LOG.info("Checking for delayed deliveries...");

        Iterable<Delivery> deliveries = deliveryService.findDelayedDeliveries();

        for (Delivery delivery : deliveries) {
            if (isDeliveryDelayed(delivery)) {
                notifyCustomerSupport(delivery);
            }
        }
    }

    /**
     * a method to check if a delivery is delayed
     * @param delivery The delivery to check
     * @return true if the delivery is delayed, false otherwise
     */
    private boolean isDeliveryDelayed(Delivery delivery) {
        long minutesElapsed = (System.currentTimeMillis() - delivery.getStartTime().toEpochMilli()) / (1000 * 60);
        return minutesElapsed >= 45;
    }

    /**
     * This method should be called to notify customer support team
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport(Delivery delivery) {
        LOG.info("Customer support team is notified about a delayed delivery having id "+delivery.getId());
    }
}