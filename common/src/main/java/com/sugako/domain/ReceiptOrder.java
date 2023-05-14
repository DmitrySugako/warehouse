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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(exclude = {"product"})
@ToString(exclude = {"product"})
@Table(name = "receipt_order")
public class ReceiptOrder {

    @Id
    private Long id;

    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "income_data")
    private Timestamp incomeData;

    @Column(name = "receipt_status")
    private Boolean receiptStatus;

    @Column
    private Long quantity;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(name = "l_products_incoming",
            joinColumns = @JoinColumn(name = "receipt_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
   @JsonIgnoreProperties("product")
    private Set<Product> products = Collections.emptySet();
}
