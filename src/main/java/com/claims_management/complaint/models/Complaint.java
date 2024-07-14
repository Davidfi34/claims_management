package com.claims_management.complaint.models;

import com.claims_management.complaint.commonFailures.models.CommonFailures;
import com.claims_management.consortium.models.Consortium;
import com.claims_management.serviceData.models.ServiceData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Table(name = "complaints")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime date;

    //TODO: customer
    private String firstname;
    private String lastname;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private ServiceData serviceData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consortium")
    private Consortium consortium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_failures")
    private CommonFailures commonFailures;
    private boolean active;
}
