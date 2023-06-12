package com.softtek.interview.inditex.business.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessConstants {

  public static final long COUNT_ROWS_DB = 0;
  public static final int TAKE_ELEMENT_BY_GET_ALL = 1;
  public static final String ERROR_TYPE_FUNCTIONAL = "FUNCTIONAL";

  public static final String ERROR_DESCRIPTION_LABEL = "Description";
  public static final String ERROR_COD_TYPE_LABEL = "CodError";
  public static final String ERROR_TYPE_LABEL = "TypeError";
  public static final String ERROR_CODE_TYPE_FUNCTIONAL = "FU9999";
  public static final String PRODUCT_DTO_NULL_EXCEPTION = "Por favor ingrese los tres "
      + "filtros requeridos";
  public static final String DATE_FORMAT_ALLOWED =
      "^\\d{4}-\\d{2}-\\d{2}-\\d{2}\\.\\d{2}\\.\\d{2}$";
  public static final String APPLY_DATE_DESCRIPTION_API_EXCEPTION =
      "Por favor ingrese un valor valido para el parametro applyDate";
  public static final String BRAND_ID_DESCRIPTION_API_EXCEPTION =
      "Por favor ingrese un valor valido para el parametro chainId";
  public static final String PRODUCT_ID_DESCRIPTION_API_EXCEPTION =
      "Por favor ingrese un valor valido para el parametro productIdentifier";
  public static final String DATE_FORMAT_VALIDATE = "yyyy-MM-dd-HH.mm.ss";
  public static final String CONTROLLER_START = "Get controller start ----> productIdentifier={},"
      + " chainId={}, applyDate={}";
  public static final String CONTROLLER_ERROR = "Get controller error  ----> productIdentifier={},"
      + " chainId={}, applyDate={}";
  public static final String CONTROLLER_SUCCESS =
      "Get controller success ---> productIdentifier={},"
          + " chainId={}, applyDate={}";
}
