package tn.atch.acrh.ReglementService.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import tn.atch.acrh.FactureService.model.Facture;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReglement;
    
    @Column(columnDefinition = "boolean default false")
    private boolean reglementEffectue=false;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
