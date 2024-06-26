package com.claims_management.user.repository;

import com.claims_management.user.Dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.claims_management.user.model.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<UserResponse> findByFirstnameAndLastname(String firstname, String lastname);
}
