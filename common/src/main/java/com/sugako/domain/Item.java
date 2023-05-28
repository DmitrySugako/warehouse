package com.sugako.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"stock"})
@ToString(exclude = {"stock"})
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(generator = "item_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "sku_code")
    @NotNull
    @Size(min = 3, max = 100)
    private String skuCode;

    @Column
    @NotNull
    @Size(min = 4, max = 250)
    private String description;

    @Column
    @NotNull
    @Size(min = 3, max = 100)
    private String category;

    @Column
    @JsonIgnore
    @NotNull
    private Timestamp created;

    @Column
    @JsonIgnore
    @NotNull
    private Timestamp changed;

    @Column(name = "is_deleted")
    @JsonIgnore
    @NotNull
    private Boolean isDeleted;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<StockStatus> stock = Collections.emptySet();

}