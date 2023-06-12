package com.softtek.interview.inditex.exposeWeb;

import com.softtek.interview.inditex.business.model.api.ProductResponse;
import com.softtek.interview.inditex.business.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class ProductController {

  private ProductService productService;
  private ControllerMapper controllerMapper;

  @GetMapping
  public Mono<ProductResponse> findByPrice(@RequestParam String applyDate, String productIdentifier,
      String chainId) {
    return productService
        .findByParameters(controllerMapper.parseToDomain(applyDate, productIdentifier, chainId))
        .map(controllerMapper::parseDomainToResponse)
        .subscribeOn(Schedulers.boundedElastic());
  }

}
