package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public interface ScenarioParser {
    PackageRequest parseScenario(String scenario) throws APIException;
}
