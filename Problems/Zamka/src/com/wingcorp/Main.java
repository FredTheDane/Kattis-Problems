package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int L = Integer.parseInt(scanner.nextLine());
        int D = Integer.parseInt(scanner.nextLine());
        int X = Integer.parseInt(scanner.nextLine());

        int N = 0;
        int M = 0;

        // Find the minimum starting at L
        for(int i = L; i <= D; i++){
            if(digitSum(i) == X) {
                N = i;
                break;
            }
        }

        for(int i = D; i >= N; i--){
            if(digitSum(i) == X) {
                M = i;
                break;
            }
        }

        System.out.println(N);
        System.out.println(M);
    }

    public static int digitSum(Integer number){
        int sum = 0;
        String numberString = String.valueOf(number);
        for(int i = 0; i < numberString.length(); i++) {
            int j = Character.digit(numberString.charAt(i), 10);
            sum += j;
        }
        return sum;
    }
}
