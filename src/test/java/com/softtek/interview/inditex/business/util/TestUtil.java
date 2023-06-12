package com.softtek.interview.inditex.business.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static <T> List<T> readAsJsonDataFromResourceFileAsList(String fileName,
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

}
