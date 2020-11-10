package com.anhpham;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaPlayground {

    public static void main(String[] args) {
	// write your code here

        List<String> companies = Arrays.asList("Google", "Yahoo", "Facebook", "", "Twitter", "LinkedIn");
        System.out.println(companies);

        // Print only empty list count
        long emptyCount = companies.stream().filter(String::isEmpty).count();
        System.out.println(emptyCount);

        // Print company character with length > 6
        long lengthCount = companies.stream().filter(x -> x.length() > 6).count();
        System.out.println(lengthCount);

        // Create list string with length > 6
        List<String> longNameCompanies = companies.stream().filter(companyName -> companyName.length() > 6).collect(Collectors.toList());
        System.out.println(longNameCompanies);
    }
}
