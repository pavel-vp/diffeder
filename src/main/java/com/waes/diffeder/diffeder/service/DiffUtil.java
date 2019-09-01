package com.waes.diffeder.diffeder.service;

import org.springframework.data.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class
 */
public class DiffUtil {

    /**
     * Compares parameters and returns List of Pairs objects with information about starting position of difference and ength of difference.
     *
     * @param leftData - data of Left object
     * @param rightData - data of Right object
     * @return - List of Pair objects
     */
    public static List<Pair<Integer, Integer>> calculateDiff(String leftData, String rightData) {
        // Calculate difference  -
        // So mainly offsets + length in the data
        List<Pair<Integer, Integer>> result = new LinkedList<>();
        boolean isDiff = false;
        int lastPosition = 0;
        for (int i = 0; i < leftData.length(); i++) {
            if (leftData.charAt(i) != rightData.charAt(i)) {
                if (!isDiff) {
                    isDiff = true;
                    lastPosition = i;
                }
            } else {
                if (isDiff) {
                    result.add(Pair.of(lastPosition, i - lastPosition));
                    isDiff = false;
                    lastPosition = i;
                }
            }
        }
        if (isDiff) {
            result.add(Pair.of(lastPosition, leftData.length() - lastPosition));
        }

        return result;
    }

    /**
     * Concatenates the List of Pair objects to the String representation
     *
     * @param difference - List of Pair objects
     * @return - String representation of differences
     */
    public static String concatToString(List<Pair<Integer, Integer>> difference) {
        return difference.stream()
                .map(entry -> "Position: " + entry.getFirst() + ", length: " + entry.getSecond())
                .collect(Collectors.joining("; "));
    }

}
