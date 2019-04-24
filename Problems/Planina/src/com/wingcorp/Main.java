package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int iterations = Integer.parseInt(scanner.nextLine());
	    int res = countRec(iterations);
        System.out.println(res * res);
    }

    public static int countRec(int i){
        switch (i){
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 5;
            default:
                int rec = countRec(i - 1);
                return (rec - 1) + rec;
        }
    }
}
