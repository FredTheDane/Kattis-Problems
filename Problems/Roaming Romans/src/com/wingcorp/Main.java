package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    double number = Double.parseDouble(scanner.nextLine());
	    double c = (1000.0 * (5280.0/4854.0));
        System.out.println(Math.round(number * c));
    }
}
