package com.softtek.interview.inditex.business.model.api;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {

  private String productIdentifier;
  private String chainIdentifier;
  private String rateToApply;
  private String fromApplyDate;
  private String toApplyDate;
  private String finalAmount;
}
