package com.claims_management.subscriber.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.claims_management.subscriber.models.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    //TODO: SUBSCRIBER ACTIVE
    Page<Subscriber> findByEndDateNull(Pageable pageable);
}
