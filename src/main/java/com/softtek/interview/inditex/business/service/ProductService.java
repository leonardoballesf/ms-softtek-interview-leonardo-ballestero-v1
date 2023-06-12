package com.softtek.interview.inditex.business.service;

import com.softtek.interview.inditex.business.model.domain.ProductDto;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<Product> findByParameters(ProductDto productDto);
}
