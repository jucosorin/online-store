package com.jucosorin.online.store.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_type")
@SuperBuilder
@NoArgsConstructor
public class ProductType extends NamedEntity {

}