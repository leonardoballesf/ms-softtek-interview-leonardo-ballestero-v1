package com.softtek.interview.inditex.business.service.impl;

import com.softtek.interview.inditex.business.model.domain.ProductDto;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import com.softtek.interview.inditex.business.repository.ProductRepository;
import com.softtek.interview.inditex.business.service.ProductService;
import com.softtek.interview.inditex.business.util.UtilBusiness;
import com.softtek.interview.inditex.business.util.constants.BusinessConstants;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  ProductRepository productRepository;
  ProductValidator productValidator;

  @Override
  public Mono<Product> findByParameters(ProductDto productDto) {
    return productValidator.validateQueryParamsValues(productDto)
        .then(productRepository.count())
        .flatMapMany(count -> count == BusinessConstants.COUNT_ROWS_DB
            ? validateDataIntoPersistence()
            : productRepository.findAll())
        .filter(productValidator.filterWithQueriesParamsValues(productDto))
        .sort(Comparator.comparing(Product::getPriority)
            .reversed())
        .take(BusinessConstants.TAKE_ELEMENT_BY_GET_ALL)
        .singleOrEmpty();
  }

  private Flux<Product> validateDataIntoPersistence() {
    return Flux.defer(() ->
        {
          productRepository.saveAll(UtilBusiness.listFirstLoad)
              .subscribeOn(Schedulers.boundedElastic())
              .subscribe();
          return Flux.empty();
        }
    ).thenMany(Flux.fromIterable(UtilBusiness.listFirstLoad));
  }
}
