package com.claims_management.adm.repository;


import com.claims_management.adm.models.Adm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmRepository extends JpaRepository<Adm, Long> {
}
