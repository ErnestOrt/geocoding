package org.ernest.geocoding.parser;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class FileParserImpl implements FileParser {

    @Override
    public List<String> parse(byte[] content) {
        List<String> addresses = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(new String(content), "\n");
        while (stringTokenizer.hasMoreElements()) {
            addresses.add(stringTokenizer.nextElement().toString().trim());
        }

        return addresses;
    }
}
