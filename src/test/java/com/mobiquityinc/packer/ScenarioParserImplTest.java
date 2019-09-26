package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScenarioParserImplTest {

   @Test
    public void testParseScenarioWithCorrectInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        PackageRequest packageRequest = scenarioParser.parseScenario(scenario);
        assertEquals(packageRequest.getItems().size(), 6);
        assertEquals(packageRequest.getCapacity().intValue(), 81);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithCorruptCapacityInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 - (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithZeroWeightInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81: (1,0,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithBigWeightInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : (1,200,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithCorruptWeightInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : (1,53.3822,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithBigCostInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : (1,53.38,€200) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithEmptyItemInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : ";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithCorruptItemInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        String scenario = "81 : (1000000000000,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        scenarioParser.parseScenario(scenario);
    }

    @Test(expected = APIException.class)
    public void testParseScenarioWithEmptyInput() throws Exception {
        ScenarioParser scenarioParser = new ScenarioParserImpl();
        scenarioParser.parseScenario("");
    }
}