package com.clicktravel.chess.service;

import com.clicktravel.chess.exception.DecodingExcpetion;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arthur on 19/12/16.
 */
public class Decoder {

    private static char[] VALID_CHARS = {'.', 'p', 'k', 'q', 'b', 'r', 'n'};

    public static List<String> decode(String encoded) throws DecodingExcpetion {

        String[] rows = encoded.split("/");

        if (rows.length != 8) {
            throw new DecodingExcpetion("Wrong number of rows");
        }

        List<String> decoded = Arrays.stream(rows).map(row -> decodeRow(row)).collect(Collectors.toList());

        for (String row: decoded) {
            if (row.length() != 8) {
                throw new DecodingExcpetion("Wrong number of squares on row");
            }

            if (!StringUtils.containsOnly(row.toLowerCase(), VALID_CHARS)) {
                throw new DecodingExcpetion("Unexpected character");
            }
        }

        return decoded;
    }

    private static String decodeRow(String row) {
        StringBuilder sb = new StringBuilder();

        for (char c : row.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            if (numericValue > 0 && numericValue < 10) {
                sb.append(StringUtils.repeat('.', numericValue));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
