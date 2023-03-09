package com.suspro.productmgmt.repository;

import com.suspro.productmgmt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
