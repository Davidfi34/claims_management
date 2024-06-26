package com.claims_management.serviceData.repository;


import com.claims_management.serviceData.models.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDataRepository extends JpaRepository<ServiceData, Long> {
}
