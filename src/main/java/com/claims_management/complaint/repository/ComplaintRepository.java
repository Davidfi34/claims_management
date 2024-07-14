package com.claims_management.complaint.repository;

import com.claims_management.complaint.models.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    //TODO: COMPLAINT ACTIVE
    Page<Complaint> findByActiveTrue(Pageable pageable);
}
