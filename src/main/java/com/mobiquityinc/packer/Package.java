package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The class {@code Package} is wrapper for selected {@code items} and total {@code cost} of package
 * and has a method for returning package data in String format
 *
 * @author Khadijeh Ghamilouie
 */
public class Package {

    private static final String ITEM_DELIMITER = ",";
    private static final String DASH = "-";

    private List<Item> items;
    private Integer cost;

    public Package(List<Item> items, Integer cost) {
        this.items = items;
        this.cost = cost;
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getCost() {
        return cost;
    }

    /**
     * @return  String output consists of index of items that are separated by comma
     * if {@code items} is null or empty dash is added to output
     */
    public String outputString() {
        if (getItems() == null || getItems().isEmpty()) {
            return DASH;
        }
        return items.stream()
                .map(i -> String.valueOf(i.getIndex()))
                .collect(Collectors.joining(ITEM_DELIMITER));
    }
}
