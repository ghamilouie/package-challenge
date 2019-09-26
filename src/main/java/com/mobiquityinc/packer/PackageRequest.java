package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.util.List;

public abstract class PackageRequest {

    protected static final int MAX_CAPACITY = 100;
    protected List<Item> items;
    protected Integer capacity;

    public PackageRequest(List<Item> items, Integer capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getCapacity() {
        return capacity;
    }

    abstract Package createPackage() throws APIException ;
}
