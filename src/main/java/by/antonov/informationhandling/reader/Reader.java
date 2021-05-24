package by.antonov.informationhandling.reader;

import by.antonov.informationhandling.exception.CustomException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reader {

  private static final Logger logger = LogManager.getLogger();

  public String readDataFromFile(String filepath)
      throws CustomException {
    logger.info(String.format("Reading file %s.", filepath));
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filepath);

    if (inputStream == null) {
      logger.info(String.format("Can not find file %s", filepath));
      throw new CustomException(String.format("Can not find file %s", filepath));
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    return reader.lines().collect(Collectors.joining("\n"));
  }
}
