package com.sugako.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"stock"})
@ToString(exclude = {"stock"})
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(generator = "shipment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "shipment_id_seq", sequenceName = "shipment_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "shipment_number")
    private Long shipmentNumber;

    @Column(name = "shipment_status")
    private Boolean shipmentStatus;

    @Column(name = "remaining_quantity")
    private Long remainingQuantity;

    @Column
    @JsonIgnore
    private Timestamp created;

    @Column
    @JsonIgnore
    private Timestamp changed;

    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonManagedReference
    private StockStatus stock;


}




