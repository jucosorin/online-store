package com.jucosorin.online.store.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_type")
@Builder
@NoArgsConstructor
public class ProductType extends NamedEntity {

}