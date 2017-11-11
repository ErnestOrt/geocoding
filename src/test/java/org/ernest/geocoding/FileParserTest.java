package org.ernest.geocoding;

import org.apache.commons.io.IOUtils;
import org.ernest.geocoding.parser.FileParserImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FileParserTest {

    @Test
    public void givenFileContentWhenParsingThenExpectedAddressesAreRetrieved() throws IOException {
        List<String> addresses = new FileParserImpl().parse(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("input_addresses.txt")).getBytes());

        assertEquals(13, addresses.size());
        assertEquals("Barcelona, Barcelona", addresses.get(0));
        assertEquals("Universidade de Campinas, Brasil", addresses.get(addresses.size()-1));
    }
}
