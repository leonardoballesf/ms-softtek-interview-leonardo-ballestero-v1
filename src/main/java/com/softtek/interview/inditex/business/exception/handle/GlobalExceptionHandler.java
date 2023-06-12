package com.softtek.interview.inditex.business.exception.handle;

import com.softtek.interview.inditex.business.exception.InvalidParameterException;
import com.softtek.interview.inditex.business.util.constants.BusinessConstants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(WebExchangeBindException.class)
  public Mono<ResponseEntity<Map<String, Object>>> handlerException(WebExchangeBindException ex) {
    Map<String, Object> response = new HashMap<>();
    return Mono.just(ex).map(WebExchangeBindException::getMessage).flatMap(msg -> {
      response.put(BusinessConstants.ERROR_DESCRIPTION_LABEL, ex.getMessage());
      return Mono.just(ResponseEntity.status(ex.getStatus())
          .body(response));
    });
  }

  @ExceptionHandler(InvalidParameterException.class)
  public Mono<ResponseEntity<Map<String, Object>>> handlerException(InvalidParameterException ex) {
    Map<String, Object> response = new HashMap<>();
    return Mono.just(ex).map(InvalidParameterException::getMessage).flatMap(msg -> {
      response.put(BusinessConstants.ERROR_COD_TYPE_LABEL,
          BusinessConstants.ERROR_CODE_TYPE_FUNCTIONAL);
      response.put(BusinessConstants.ERROR_DESCRIPTION_LABEL, ex.getMessage());
      response.put(BusinessConstants.ERROR_TYPE_LABEL, BusinessConstants.ERROR_TYPE_FUNCTIONAL);
      return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(response));
    });

  }

  @ExceptionHandler(ServerWebInputException.class)
  public Mono<ResponseEntity<Map<String, Object>>> handlerException(ServerWebInputException ex) {
    Map<String, Object> response = new HashMap<>();
    return Mono.just(ex).map(ServerWebInputException::getMessage).flatMap(msg -> {
      response.put(BusinessConstants.ERROR_DESCRIPTION_LABEL,
          BusinessConstants.PRODUCT_DTO_NULL_EXCEPTION);
      return Mono.just(ResponseEntity.status(ex.getStatus())
          .body(response));
    });
  }
}
