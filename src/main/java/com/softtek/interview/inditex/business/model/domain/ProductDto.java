package com.softtek.interview.inditex.business.model.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  @NotNull
  private String applyDate;
  @NotNull
  private String productIdentifier;
  @NotNull
  private String chainId;
}
