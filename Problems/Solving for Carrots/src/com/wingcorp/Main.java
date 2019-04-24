package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        int P = Integer.parseInt(parts[1]);

        for(int i = 0; i < N; i++){
            scanner.nextLine();
        }

        System.out.println(P);
    }
}
