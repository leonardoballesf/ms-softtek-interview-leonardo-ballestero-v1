package com.softtek.interview.inditex.business.service.impl;

import com.softtek.interview.inditex.business.model.domain.ProductDto;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import com.softtek.interview.inditex.business.repository.ProductRepository;
import com.softtek.interview.inditex.business.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  ProductRepository priceRepository;

  @Mock
  ProductValidator priceValidator;


  @ParameterizedTest
  @CsvFileSource(resources = "/dataParameterizerTest.csv", numLinesToSkip = 1)
  @DisplayName("Return successfully when into request params are names correct and values allowed")
  void returnSuccessFullWhenRequestParamsIsValid(String applyDate,
      String productIdentifier, String chainId, String productIdentifierDb, String chainIdDb,
      String priceListDb, String fromDateDb, String toDateDb, String finalAmountDb) {

    ProductDto princeRequest = new ProductDto();
    princeRequest.setApplyDate(applyDate);
    princeRequest.setProductIdentifier(productIdentifier);
    princeRequest.setChainId(chainId);
    List<Product> priceList = TestUtil.readAsJsonDataFromResourceFileAsList(
        "listDataSource.json", Product.class);

    Mockito.when(priceRepository.findAll())
        .thenReturn(Flux.fromIterable(priceList));
    Mockito.when(priceRepository.count())
        .thenReturn(Mono.just(1L));

    StepVerifier.create(productService.findByParameters(princeRequest))
        .expectNextMatches(response -> response.getCurrentamount().equals(finalAmountDb)
            && response.getProductid().equals(productIdentifierDb)
            && response.getBrandid().equals(chainIdDb)
            && response.getPricelist().equals(priceListDb)
            && response.getStartdate().equals(fromDateDb)
            && response.getEnddate().equals(toDateDb)).expectComplete()
        .verify();
  }


}
