package com.softtek.interview.inditex.business.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.softtek.interview.inditex.business.model.thirdparty.Product;
import com.softtek.interview.inditex.business.util.constants.BusinessConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UtilBusiness {

  private static final ObjectMapper mapper = new ObjectMapper();

  static final String EMPTY = " ";
  static final String FILE_DATA_SOURCE = "SQL/data.json";

  /**
   * read as json data from resource file as list. * * @param fileName fileName * @param resultClass
   * resultClass *
   *
   * @param <T> return class * @return object
   */
  private static <T> List<T> readAsJsonDataFromResourceFileAsList(String fileName,
      Class<T> resultClass) {
    try {
      CollectionType listType = mapper.getTypeFactory()
          .constructCollectionType(ArrayList.class, resultClass);
      return mapper.readValue(UtilBusiness.class.getClassLoader().getResourceAsStream(fileName),
          listType);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static final List<Product> listFirstLoad =
      readAsJsonDataFromResourceFileAsList(FILE_DATA_SOURCE,
          Product.class).stream()
          .map(setCurrentAmount())
          .collect(Collectors.toList());

  private static Function<Product, Product> setCurrentAmount() {
    return price -> {
      price.setCurrentamount(String.valueOf(new StringBuilder().append(price.getCurrency())
          .append(EMPTY)
          .append(price.getPrice())));
      return price;
    };
  }

  public static boolean patternMatch(String stringCompare, String pattern) {

    return Pattern.compile(pattern).matcher(stringCompare).matches();
  }

  public static LocalDateTime formatterDate(String date) {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(BusinessConstants.DATE_FORMAT_VALIDATE);
    return LocalDateTime.parse(date, formatter);

  }


}