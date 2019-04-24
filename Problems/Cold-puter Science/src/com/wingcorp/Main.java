package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        int count = 0;
        String[] temps = scanner.nextLine().split(" ");

        for(String temp : temps) {
            int t = Integer.parseInt(temp);
            if (t < 0) count++;
        }

        System.out.println(count);
    }
}
