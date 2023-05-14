package com.sugako.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "analytics")
public class ProductAnalytics {

    @Id
    private Long id;

    @Column(name = "batch_number")
    private Long batchNumber;

    @Column(name = "country_of_import")
    private String countryOfImport;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(name = "l_product_analytics",
            joinColumns = @JoinColumn(name = "analytics_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))

    @JsonIgnoreProperties("analytics")
    private Set<Product> product = Collections.emptySet();
}