package com.jucosorin.online.store.repositories;

import com.jucosorin.online.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select count(distinct p) from Product p where upper(p.productType.name) = upper(:name)")
    long contByProductType(@Param("name") String name);

}
