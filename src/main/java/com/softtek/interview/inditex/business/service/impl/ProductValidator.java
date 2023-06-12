package com.softtek.interview.inditex.business.service.impl;

import com.softtek.interview.inditex.business.exception.InvalidParameterException;
import com.softtek.interview.inditex.business.model.domain.ProductDto;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import com.softtek.interview.inditex.business.util.UtilBusiness;
import com.softtek.interview.inditex.business.util.constants.BusinessConstants;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductValidator {

  public final Mono<ProductDto> validateQueryParamsValues(ProductDto productDto) {
    return Mono.justOrEmpty(productDto)
        .filter(dto -> !Objects.isNull(dto.getApplyDate()) && UtilBusiness.patternMatch(
            dto.getApplyDate(), BusinessConstants.DATE_FORMAT_ALLOWED))
        .switchIfEmpty(Mono.error(() -> new InvalidParameterException(
            BusinessConstants.APPLY_DATE_DESCRIPTION_API_EXCEPTION)))
        .filter(dto -> !Objects.isNull(dto.getChainId()) && !Strings.isBlank(dto.getChainId()))
        .switchIfEmpty(Mono.error(() -> new InvalidParameterException(
            BusinessConstants.BRAND_ID_DESCRIPTION_API_EXCEPTION)))
        .filter(dto -> !Objects.isNull(dto.getProductIdentifier()) && !Strings.isBlank(
            dto.getProductIdentifier()))
        .switchIfEmpty(Mono.error(() -> new InvalidParameterException(
            BusinessConstants.PRODUCT_ID_DESCRIPTION_API_EXCEPTION)));
  }

  public final Predicate<Product> filterWithQueryParamsValues(ProductDto productDto) {
    return product ->
        isValidDateTime(productDto, product)
            && (productDto.getProductIdentifier().equals(product.getProductid()))
            && (productDto.getChainId().equals(product.getBrandid()));
  }

  private boolean isValidDateTime(ProductDto priceDto, Product priceProduct) {
    LocalDateTime applyDateTime = UtilBusiness.formatterDate(priceDto.getApplyDate());
    LocalDateTime startDateTime = UtilBusiness.formatterDate(priceProduct.getStartdate());
    LocalDateTime endDateTime = UtilBusiness.formatterDate(priceProduct.getEnddate());
    return (applyDateTime.isAfter(startDateTime) || applyDateTime.isEqual(startDateTime))
        && (applyDateTime.isBefore(endDateTime) || applyDateTime.isEqual(endDateTime));
  }

}