package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] swapString = scanner.nextLine().toCharArray();

	    int loc = 1;
	    for(char c : swapString){
            loc = swap(c, loc);
        }
        System.out.println(loc);
    }

    static int swap(char swapType, int loc){
        switch (swapType) {
            case 'A':
                if (loc == 1) return 2;
                else if (loc == 2) return 1;
                else return 3;
            case 'B':
                if (loc == 2) return 3;
                else if (loc == 3) return 2;
                else return 1;
            case 'C':
                if (loc == 1) return 3;
                else if (loc == 3) return 1;
                else return 2;
            default:
                return loc;
        }
    }
}
