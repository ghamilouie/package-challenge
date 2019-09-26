package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @author Khadijeh Ghamilouie
 */
public class PackerTest {

    @Test
    public void testPackFileWithMultipleLineInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("multi-line-input.txt").toURI();
        String pack = Packer.pack(Paths.get(uri).toString());
        assertEquals(pack, "4-2,78,9");
    }

    @Test
    public void testPackFileWithSingleLineInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("single-line-input.txt").toURI();
        String pack = Packer.pack(Paths.get(uri).toString());
        assertEquals(pack, "4");
    }

    @Test(expected = APIException.class)
    public void testPackFileWithZeroCapacityInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("zero-capacity-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackFileWithNegativeCapacityInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("negative-capacity-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithCorruptCapacityInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("corrupt-capacity-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackFileWithZeroWeightInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("zero-weight-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithBigWeightInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("big-weight-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithBigCostInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("big-cost-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithEmptyItemInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("empty-item-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithCorruptItemInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("corrupt-item-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithEmptyFile() throws Exception {
        URI uri = ClassLoader.getSystemResource("empty-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithEmptyLineInput() throws Exception {
        URI uri = ClassLoader.getSystemResource("empty-line-input.txt").toURI();
        Packer.pack(Paths.get(uri).toString());
    }

    @Test(expected = APIException.class)
    public void testPackWithFileNoteExist() throws Exception {
        Packer.pack("not-exist.txt");
    }
}