package com.claims_management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.claims_management.user.model.User;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
