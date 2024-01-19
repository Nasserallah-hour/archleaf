package tn.atch.acrh.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private Long price;
    @ManyToMany
    private Set<Facture> facture;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category")
    private ProductCategory category;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
