package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * @author Khadijeh Ghamilouie
 */
public class PackerTest {

    @Test
    public void testPackFileWithMultipleLineInput() throws Exception {
        URL resource = this.getClass().getResource("/multi-line-input.txt");
        String pack = Packer.pack(resource.getPath());
        assertEquals(pack, "4-2,78,9");
    }

    @Test
    public void testPackFileWithSingleLineInput() throws Exception {
        URL resource = this.getClass().getResource("/single-line-input.txt");
        String pack = Packer.pack(resource.getPath());
        assertEquals(pack, "4");
    }

    @Test(expected = APIException.class)
    public void testPackFileWithZeroCapacityInput() throws Exception {
        URL resource = this.getClass().getResource("/zero-capacity-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackFileWithNegativeCapacityInput() throws Exception {
        URL resource = this.getClass().getResource("/negative-capacity-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithCorruptCapacityInput() throws Exception {
        URL resource = this.getClass().getResource("/corrupt-capacity-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackFileWithZeroWeightInput() throws Exception {
        URL resource = this.getClass().getResource("/zero-weight-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithBigWeightInput() throws Exception {
        URL resource = this.getClass().getResource("/big-weight-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithBigCostInput() throws Exception {
        URL resource = this.getClass().getResource("/big-cost-input.txt");
        Packer.pack(resource.getPath());
    }


    @Test(expected = APIException.class)
    public void testPackWithEmptyItemInput() throws Exception {
        URL resource = this.getClass().getResource("/empty-item-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithCorruptItemInput() throws Exception {
        URL resource = this.getClass().getResource("/corrupt-item-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithEmptyFile() throws Exception {
        URL resource = this.getClass().getResource("/empty-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithEmptyLineInput() throws Exception {
        URL resource = this.getClass().getResource("/empty-line-input.txt");
        Packer.pack(resource.getPath());
    }

    @Test(expected = APIException.class)
    public void testPackWithFileNoteExist() throws Exception {
        Packer.pack("not-exist.txt");
    }
}