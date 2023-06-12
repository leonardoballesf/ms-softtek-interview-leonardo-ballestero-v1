package com.softtek.interview.inditex.business.repository;

import com.softtek.interview.inditex.business.model.thirdparty.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {

}
