package com.jucosorin.online.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Product extends NamedEntity {

    private int stock;

    @Column(precision = 9, scale = 4, nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

}