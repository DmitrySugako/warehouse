package com.sugako.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"address", "receipt", "item", "shipments"})
@ToString(exclude = {"address", "receipt", "item", "shipments"})
@Table(name = "stock_status")
public class StockStatus {

    @Id
    @GeneratedValue(generator = "item_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    @NotNull
    private Item item;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    @JsonBackReference
    @NotNull
    private ReceiptOrder receipt;

    @Column(name = "ordered_quantity")
    @NotNull
    @PositiveOrZero
    private Long orderedQuantity;

    @Column(name = "available_quantity")
    @NotNull
    @PositiveOrZero
    private Long availableQuantity;

    @Column(name = "reserved_quantity")
    @NotNull
    @PositiveOrZero
    private Long reservedQuantity;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private StorageAddress address;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonBackReference
    private Set<Shipment> shipments = Collections.emptySet();

    @Column
    @JsonIgnore
    private Timestamp created;

    @Column
    @JsonIgnore
    private Timestamp changed;

    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;


}
