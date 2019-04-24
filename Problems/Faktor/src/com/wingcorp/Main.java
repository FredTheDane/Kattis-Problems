package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        double A = Integer.parseInt(params[0]);
        double I = Integer.parseInt(params[1]);

        double latest = A * I;

        for(double i = latest; i > 0; i--){
            if (Math.ceil(i/A) >= I){
                latest = i;
            } else {
                System.out.println((int)latest);
                break;
            }
        }
    }
}
