package com.softtek.interview.inditex.exposeWeb;

import com.softtek.interview.inditex.business.model.api.ProductResponse;
import com.softtek.interview.inditex.business.model.domain.ProductDto;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ControllerMapper {

  ProductDto parseToDomain(String applyDate, String productIdentifier,String chainId);
  @Mapping(source = "productid", target = "productIdentifier")
  @Mapping(source = "brandid", target = "chainIdentifier")
  @Mapping(source = "startdate", target = "fromApplyDate")
  @Mapping(source = "enddate", target = "toApplyDate")
  @Mapping(source = "pricelist", target = "rateToApply")
  @Mapping(source = "currentamount", target = "finalAmount")
  ProductResponse parseDomainToResponse(Product domain);
}
