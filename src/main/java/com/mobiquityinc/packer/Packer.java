package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class {@code Packer} is main class with main method that is interface for using this library
 * main method is {@code pack} which get absolute file path and returns string output
 *
 * @author Khadijeh Ghamilouie
 */
public class Packer {

    /**
     * Read file, collect lines and process each line for finding package and create output string
     *
     * @param filePath the absolute path of file
     * @return String output, each line consists of one package with index of items that are separated by comma,
     * if there is no package for scenario dash is added to output, each line is separated by \n
     * @throws APIException If any of the following is true:
     *                      <ul>
     *                      <li> {@code file} is not exist
     *                      <li> {@code file} is empty
     *                      </ul>
     */
    public static String pack(String filePath) throws APIException {
        try (Stream<String> lineStream = Files.lines(Paths.get(filePath))) {
            List<String> lines = lineStream.collect(Collectors.toList());
            if (lines.isEmpty()) {
                throw new APIException("File is empty: '" + filePath + "'");
            }
            StringBuilder result = new StringBuilder();
            for (String line : lines) {
                ScenarioParser scenarioParser = new ScenarioParserImpl();
                PackageRequest packageRequest = scenarioParser.parseScenario(line);
                Package aPackage = packageRequest.createPackage();
                result.append(aPackage.outputString());
            }
            return result.toString();
        } catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }
    }
}
