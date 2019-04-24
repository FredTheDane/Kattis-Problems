package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int r1 = Integer.parseInt(new StringBuilder(parts[0]).reverse().toString());
        int r2 = Integer.parseInt(new StringBuilder(parts[1]).reverse().toString());

        int bigger = r1 > r2 ? r1 : r2;
        System.out.println(bigger);
    }
}
