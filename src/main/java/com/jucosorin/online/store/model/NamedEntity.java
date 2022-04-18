package com.jucosorin.online.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class NamedEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
}