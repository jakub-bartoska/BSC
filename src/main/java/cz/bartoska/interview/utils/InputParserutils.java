package cz.bartoska.interview.utils;

import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.model.WeightPostCodePair;

import java.io.File;
import java.math.BigDecimal;

/**
 * Util class for input parsing
 */
public class InputParserutils {

    /**
     * Parses text in format <big decimal><white space><post code> to {@link WeightPostCodePair}.
     * e.g. 10.5 50303
     */
    public static WeightPostCodePair inputToWeightPostPair(String input) {
        String[] values = input.split(" ");
        return new WeightPostCodePair(values[1], new BigDecimal(values[0]));
    }

    /**
     * Parses text in format <command><white space><path to file> to {@link File}.
     * e.g. add-file C:\Users\file.txt
     */
    public static File inputToFile(String input) {
        String[] args = input.split(" ");
        return new File(args[1]);
    }

    /**
     * Parses text in format <big decimal><white space><post code> to {@link WeightFeePair}.
     * e.g. 10.5 11.5
     */
    public static WeightFeePair inputToWeightFeePair(String input) {
        String[] values = input.split(" ");
        return new WeightFeePair(new BigDecimal(values[0]), new BigDecimal(values[1]));
    }
}
