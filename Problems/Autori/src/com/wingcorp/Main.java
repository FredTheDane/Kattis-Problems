package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split("-");
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < parts.length; i++){
            result.append(parts[i].charAt(0));
        }

        System.out.println(result.toString());
    }
}
