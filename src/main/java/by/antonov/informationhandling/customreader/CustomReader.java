package by.antonov.informationhandling.customreader;

import by.antonov.informationhandling.exception.CustomException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class CustomReader {
  public String readDataFromFile(String filepath) throws CustomException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filepath);

    if (inputStream == null) {
      throw new CustomException("Can not find file " + filepath);
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    return reader.lines().collect(Collectors.joining("\n"));
  }
}
