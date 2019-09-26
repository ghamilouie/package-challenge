package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The class {@code PackageRequestImpl} is wrapper for {@code items} and {@code capacity}
 * and method {@code createPackage} for creating package that total weight is less than or equal to the package limit
 * and the total cost is as large as possible
 *
 * @author Khadijeh Ghamilouie
 */
public class PackageRequestImpl extends PackageRequest {

    private static final int FLOAT_POINT = 100;

    public PackageRequestImpl(List<Item> items, Integer capacity) {
        super(items, capacity);
    }
    /**
     * Create new Package that total weight is less than or equal to the package limit
     * and the total cost is as large as possible, for creating package dynamic programing solution of
     * 0/1 knapsack algorithm is used by multiple weight in  {@code FLOAT_POINT}
     * as dynamic programing for this algorithm needs int data type, default value for {@code FLOAT_POINT} is 100
     *
     * @return new Package with selected items and total cost
     * @throws APIException If any of the following is true:
     *                      <ul>
     *                      <li> {@code items} is null or empty
     *                      <li> {@code capacity} is zero
     *                      <li> {@code capacity} is negative
     *                      <li> {@code capacity} is greater than {@code MAX_CAPACITY}
     *                      </ul>
     */
    public Package createPackage() throws APIException {
        validate();
        this.capacity = capacity * FLOAT_POINT;
        int itemsCount = items.size();
        items.sort(Comparator.comparing(Item::getWeight));
        int[][] table = new int[itemsCount + 1][capacity + 1];
        for (int i = 0; i <= capacity; i++)
            table[0][i] = 0;
        for (int i = 1; i <= itemsCount; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (items.get(i - 1).getWeight() * FLOAT_POINT > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - (int) (items.get(i - 1).getWeight() * FLOAT_POINT)] + items.get(i - 1).getCost());
                }
            }
        }
        int resultCost = table[itemsCount][capacity];
        int resultWeight = capacity;
        List<Item> selectedItems = new ArrayList<>();
        for (int i = itemsCount; i > 0 && resultCost > 0; i--) {
            if (resultCost != table[i - 1][resultWeight]) {
                selectedItems.add(items.get(i - 1));
                resultWeight -= items.get(i - 1).getWeight() * FLOAT_POINT;
                resultCost -= items.get(i - 1).getCost();
            }
        }
        selectedItems.sort(Comparator.comparing(Item::getIndex));
        return new Package(selectedItems, table[itemsCount][capacity]);
    }

    private void validate() throws APIException {
        if (capacity == null || capacity <= 0) {
            throw new APIException("The capacity should not be null, zero or negative ");
        }
        if (capacity > MAX_CAPACITY) {
            throw new APIException("The capacity should not be greater than: " + MAX_CAPACITY);
        }
        if (items == null || items.isEmpty()) {
            throw new APIException("There is no item for creating package");
        }
    }
}

