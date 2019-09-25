package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            if (lines == null || lines.isEmpty()) {
                throw new APIException("File is empty: '" + filePath + "'");
            }
            StringBuilder result = new StringBuilder();
            for (String line : lines) {
                PackageRequest packageRequest = parseLine(line);
                Package aPackage = packageRequest.createPackage();
                result.append(aPackage.outputString());
            }
            return result.toString();
        } catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Pars line for extracting maximum capacity and items based on convension pattern,
     * each line is an specific scenario by pattern:
     * <p>
     * capacity : (index, weight, cost) (index, weight, cost) ... (index, weight, cost)
     *
     * @param line the string of line to be parsed
     * @return new PackageRequest with extracted items and maximum capacity from line
     * @throws APIException If any of the following is true:
     *                      <ul>
     *                      <li> {@code line} is empty
     *                      <li> {@code capacity} of line is not extracted
     *                      <li> {@code items} of {@code line} could not be extracted
     *                      <li> there is no {@code item} in line
     *                      <li> {@code item.weight} is zero
     *                      <li> {@code item.weight} is greater than {@code MAX_ITEM_WEIGHT}
     *                      <li> {@code item.cost} is greater than {@code MAX_ITEM_COST}
     *                      </ul>
     */
    private static PackageRequest parseLine(String line) throws APIException {
        if (line.trim().equals("")) {
            throw new APIException("Current line is empty");
        }

        Integer capacity;
        try {
            capacity = Integer.valueOf(line.substring(0, line.indexOf(':')).trim());
        } catch (Exception e) {
            throw new APIException("Could not extract the capacity of current line: '" + line + "'");
        }
        Pattern pattern = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),€?(\\d+)\\)");
        Matcher m = pattern.matcher(line.substring(line.indexOf(':')).trim());
        List<Item> items = new ArrayList<>();
        while (m.find()) {
            try {
                Integer index = Integer.valueOf(m.group(1));
                Float weight = Float.valueOf(m.group(2));
                Integer cost = Integer.valueOf(m.group(3));
                Item item = new Item(index, weight, cost);
                item.validate();
                items.add(item);
            } catch (Exception e) {
                throw new APIException("Could not extract items of current line: '" + line + "'");
            }
        }
        if (items.isEmpty()) {
            throw new APIException("There is no item in current line: '" + line + "'");
        }
        return new PackageRequest(items, capacity);
    }
}
