package org.ernest.geocoding.parser;


import java.util.List;

public interface FileParser {

    List<String> parse(byte[] content);
}
