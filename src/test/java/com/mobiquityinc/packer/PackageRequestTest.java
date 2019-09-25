package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Khadijeh Ghamilouie
 */
public class PackageRequestTest {

    @Test
    public void testCreatePackageFirst() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        items.add(new Item(2, 88.62f, 98));
        items.add(new Item(3, 78.48f, 3));
        items.add(new Item(4, 72.30f, 76));
        items.add(new Item(5, 30.18f, 9));
        items.add(new Item(5, 46.34f, 48));
        PackageRequest packageRequest = new PackageRequest(items, 81);
        Package aPackage = packageRequest.createPackage();
        assertEquals(aPackage.getItems().size(), 1);
        assertEquals(aPackage.getItems().get(0).getIndex().intValue(), 4);
        assertEquals(aPackage.getCost().intValue(), 76);
    }

    @Test
    public void testCreatePackageSecond() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        items.add(new Item(2, 15.3f, 34));
        PackageRequest packageRequest = new PackageRequest(items, 8);
        Package aPackage = packageRequest.createPackage();
        assertEquals(aPackage.getItems().size(), 0);
        assertEquals(aPackage.getCost().intValue(), 0);
    }

    @Test
    public void testCreatePackageThird() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 85.31f, 29));
        items.add(new Item(2, 14.55f, 74));
        items.add(new Item(3, 3.98f, 16));
        items.add(new Item(4, 26.24f, 55));
        items.add(new Item(5, 63.69f, 52));
        items.add(new Item(6, 76.25f, 75));
        items.add(new Item(7, 60.02f, 74));
        items.add(new Item(8, 93.18f, 35));
        items.add(new Item(9, 89.95f, 78));
        PackageRequest packageRequest = new PackageRequest(items, 75);
        Package aPackage = packageRequest.createPackage();
        assertEquals(aPackage.getItems().size(), 2);
        assertEquals(aPackage.getItems().get(0).getIndex().intValue(), 2);
        assertEquals(aPackage.getItems().get(1).getIndex().intValue(), 7);
        assertEquals(aPackage.getCost().intValue(), 148);
    }

    @Test
    public void testCreatePackageForth() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 90.72f, 13));
        items.add(new Item(2, 33.80f, 40));
        items.add(new Item(3, 43.15f, 10));
        items.add(new Item(4, 37.97f, 16));
        items.add(new Item(5, 46.81f, 36));
        items.add(new Item(6, 48.77f, 79));
        items.add(new Item(7, 81.80f, 45));
        items.add(new Item(8, 19.36f, 79));
        items.add(new Item(9, 6.76f, 64));
        PackageRequest packageRequest = new PackageRequest(items, 56);
        Package aPackage = packageRequest.createPackage();
        assertEquals(aPackage.getItems().size(), 2);
        assertEquals(aPackage.getItems().get(0).getIndex().intValue(), 8);
        assertEquals(aPackage.getItems().get(1).getIndex().intValue(), 9);
        assertEquals(aPackage.getCost().intValue(), 143);
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithEmptyItems() throws Exception {
        List<Item> items = new ArrayList<>();
        PackageRequest packageRequest = new PackageRequest(items, 50);
        packageRequest.createPackage();
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithNullItems() throws Exception {
        PackageRequest packageRequest = new PackageRequest(null, 50);
        packageRequest.createPackage();
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithNullCapacity() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        PackageRequest packageRequest = new PackageRequest(items, null);
        packageRequest.createPackage();
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithZeroCapacity() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        PackageRequest packageRequest = new PackageRequest(items, 0);
        packageRequest.createPackage();
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithNegativeCapacity() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        PackageRequest packageRequest = new PackageRequest(items, -50);
        packageRequest.createPackage();
    }

    @Test(expected = APIException.class)
    public void testCreatePackageWithBigCapacity() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 53.38f, 45));
        PackageRequest packageRequest = new PackageRequest(items, 200);
        packageRequest.createPackage();
    }
}