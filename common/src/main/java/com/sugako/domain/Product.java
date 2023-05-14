package com.sugako.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"analytics", "receipt_order"})
@ToString(exclude = {"analytics", "receipt_order"})
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "product_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1, initialValue = 100)
    private Long id;

    @Column
    private String sku;

    @Column
    private String description;

    @Column
    private String srp1;

    @Column
    private String srp2;

    @Column
    private String srp3;

    @Column
    private Double weight;

    @Column
    private Long barcode;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("product")
    private Set<ProductAnalytics> analytics = Collections.emptySet();



    @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("product")
    private Set<ReceiptOrder> receipts = Collections.emptySet();
}
