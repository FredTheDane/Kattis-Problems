package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int W = Integer.parseInt(params[1]);
        int H = Integer.parseInt(params[2]);

        double hyp = Math.sqrt(Math.pow(W, 2) + Math.pow(H, 2));

        for(int i = 0; i < N; i++){
            double num = Integer.parseInt(scanner.nextLine());
            if(num <= hyp) System.out.println("DA");
            else System.out.println("NE");
        }
    }
}
