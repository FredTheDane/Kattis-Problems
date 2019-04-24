package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int H = Integer.parseInt(parts[0]);
        int M = Integer.parseInt(parts[1]);

        deductTimeFrom(H, M, 45);
    }

    static void deductTimeFrom(int H, int M, int deltaM) {
        int newM = Math.floorMod(M - deltaM, 60);
        int newH = H;
        if(deltaM > M) {
            newH = Math.floorMod(newH - 1, 24);
        }

        System.out.println(newH + " " + newM);
    }
}
