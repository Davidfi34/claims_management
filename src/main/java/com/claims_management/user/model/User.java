package com.claims_management.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

}
