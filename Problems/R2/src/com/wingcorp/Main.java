package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int r1 = Integer.parseInt(parts[0]);
        int s = Integer.parseInt(parts[1]);

        System.out.println((s*2)-11);
    }
}
