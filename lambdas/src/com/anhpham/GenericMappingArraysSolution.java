package com.anhpham;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The program uses a generic method
 * to map numeric, string, or character values
 * to a shorter list of values
 *
 * And rewrite in lambda
 *
 * @author Anh Pham
 */
public class GenericMappingArraysSolution {

    public static void main(String [] args) {
        Scanner scnr = new Scanner(System.in);

        // Declare various types
        Integer mapInt;
        List<Integer> mapIntMappings = Arrays.asList(100, 200, 300, 400, 500, 600);

        Double mapDouble;
        List<Double> mapDoubleMappings = Arrays.asList(-12.0, -6.0, 0.0, 6.0, 12.0);

        String mapString;
        List<Character> mapStringMappings = Arrays.asList('A', 'E', 'M', 'S', 'W', 'Z');

        String absorbInput;           // To read a string after a number

        // Get values to map, from user input
        // int mapping
        System.out.print("\nEnter an integer value to map: ");
        mapInt = scnr.nextInt();

        int mapIntResult = mapIntMappings.stream()
                .filter((num) -> num >= mapInt)
                .mapToInt((num) -> num)
                .findFirst().orElseThrow();

        printResult(mapInt, mapIntMappings, mapIntResult);

        // double mapping
        System.out.print("\nEnter a double value to map: ");
        mapDouble = scnr.nextDouble();

        double mapDoubleResult = mapDoubleMappings.stream()
                .filter((num) -> num >= mapDouble)
                .mapToDouble((num) -> num)
                .findFirst().orElseThrow();

        printResult(mapDouble, mapDoubleMappings, mapDoubleResult);

        // string mapping
        absorbInput = scnr.nextLine();   // Absorb the <Enter> from integer input
        System.out.print("\nEnter a string value to map: ");
        mapString = scnr.nextLine();

        char mapStringResult = mapStringMappings.stream()
                .filter((c) -> c.compareTo(mapString.charAt(0)) >= 0)
                .findFirst().orElseThrow();

        printResult(mapString, mapStringMappings, mapStringResult);
    }

    /**
     * Print the result
     * @param map user value in any type
     * @param mapRange range of the map
     * @param mapResult the first value in the table
     *                  greater than or equal to the user value
     */
    private static void printResult(Object map, List<?> mapRange, Object mapResult) {
        System.out.println();
        System.out.print("Mapping range: ");
        mapRange.forEach((val) -> System.out.print(val + " "));

        System.out.println();
        System.out.println(map + " is mapped to " + mapResult);
    }

}