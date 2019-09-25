package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

/**
 * The class {@code Item} wrapper for data {@code index}, {@code weight} and {@code cost}
 *
 * @author Khadijeh Ghamilouie
 */
public class Item {

    private static final int MAX_ITEM_WEIGHT = 100;
    private static final int MAX_ITEM_COST = 100;

    private Integer index;
    private Float weight;
    private Integer cost;

    public Item(Integer index, Float weight, Integer cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Float getWeight() {
        return weight;
    }

    public Integer getCost() {
        return cost;
    }

    public void validate() throws APIException {
        if (weight > MAX_ITEM_WEIGHT) {
            throw new APIException("The weight of item should not be greater than: " + MAX_ITEM_WEIGHT);
        }
        if (weight == 0) {
            throw new APIException("The weight should not be zero");
        }
        if (cost > MAX_ITEM_COST) {
            throw new APIException("The cost of item should not be greater than: " + MAX_ITEM_COST);
        }
    }
}
