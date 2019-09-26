package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code ScenarioParserImpl} is for parsing scenario and create item list
 *
 * @author Khadijeh Ghamilouie
 */
public class ScenarioParserImpl implements ScenarioParser {

    private Pattern pattern = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),€?(\\d+)\\)");

    /**
     * <p>
     * Pars line for extracting maximum capacity and items based on convension pattern,
     * each line is an specific scenario by pattern:
     * <p>
     * capacity : (index, weight, cost) (index, weight, cost) ... (index, weight, cost)
     *
     * @param scenario the string of line to be parsed
     * @return new PackageRequestImpl with extracted items and maximum capacity from line
     * @throws APIException If any of the following is true:
     *                      <ul>
     *                      <li> {@code line} is empty
     *                      <li> {@code capacity} of line is not extracted
     *                      <li> {@code items} of {@code line} could not be extracted
     *                      <li> there is no {@code item} in line
     *                      <li> {@code item.weight} is zero
     *                      <li> {@code item.weight} is greater than {@code MAX_ITEM_WEIGHT}
     *                      <li> {@code item.weight} float point is mor than {@code MAX_ITEM_WEIGHT_FLOAT}
     *                      <li> {@code item.cost} is greater than {@code MAX_ITEM_COST}
     *                      </ul>
     */
    public PackageRequest parseScenario(String scenario) throws APIException {
        if (scenario.trim().equals("")) {
            throw new APIException("Current line is empty");
        }
        Integer capacity;
        try {
            capacity = Integer.valueOf(scenario.substring(0, scenario.indexOf(':')).trim());
        } catch (Exception e) {
            throw new APIException("Could not extract the capacity of current line: '" + scenario + "'");
        }
        Matcher m = pattern.matcher(scenario.substring(scenario.indexOf(':')).trim());
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
                throw new APIException("Could not extract items of current line: '" + scenario + "'");
            }
        }
        if (items.isEmpty()) {
            throw new APIException("There is no item in current line: '" + scenario + "'");
        }
        return new PackageRequestImpl(items, capacity);
    }
}
