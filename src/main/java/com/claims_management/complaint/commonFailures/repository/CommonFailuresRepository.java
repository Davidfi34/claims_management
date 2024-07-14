package com.claims_management.complaint.commonFailures.repository;

import com.claims_management.complaint.commonFailures.models.CommonFailures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonFailuresRepository extends JpaRepository<CommonFailures, Long> {
}
