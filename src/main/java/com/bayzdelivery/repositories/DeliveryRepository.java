package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.util.List;

@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    @Query("SELECT p FROM Person p INNER JOIN Delivery d ON p.id = d.deliveryMan.id " +
            "WHERE d.startTime >= :startDate AND d.endTime <= :endDate " +
            "GROUP BY p " +
            "ORDER BY SUM(d.comission) DESC")
    List<Person> findTop3ByCommissionBetweenOrderByCommissionDesc(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

    @Query("SELECT AVG(d.comission) FROM Delivery d")
    Double getAverageCommission();
}
