package com.claims_management.consortium.repository;

import com.claims_management.consortium.models.Consortium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsortiumRepository extends JpaRepository<Consortium, Long> {
}
