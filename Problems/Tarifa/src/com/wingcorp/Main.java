package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int plan = Integer.parseInt(scanner.nextLine());
        int months = Integer.parseInt(scanner.nextLine());
        int acc = 0;

        for(int i = 0; i < months; i++){
            int used = Integer.parseInt(scanner.nextLine());
            acc += plan - used;
        }

        System.out.println(acc + plan);
    }
}
