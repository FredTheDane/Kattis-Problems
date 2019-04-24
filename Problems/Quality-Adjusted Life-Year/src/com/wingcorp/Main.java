package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;
        int N = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < N; i++){
            String[] parts = scanner.nextLine().split(" ");
            double q = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            result += q * y;
        }
        System.out.println(result);
    }
}
