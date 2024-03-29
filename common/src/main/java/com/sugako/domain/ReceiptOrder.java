package com.sugako.domain;


;
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
@Table(name = "receipt_order")
public class ReceiptOrder {

    @Id
    @GeneratedValue(generator = "item_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "receipt_number")
    @NotNull
    @Size(min = 3, max = 100)
    private String receiptNumber;

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


    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<StockStatus> stock = Collections.emptySet();


}
