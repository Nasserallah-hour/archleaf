package tn.atch.acrh.ClientService.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import tn.atch.acrh.DeviseService.model.Devise;
import tn.atch.acrh.FactureService.model.Facture;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Facture> factures;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Devise> devises;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
