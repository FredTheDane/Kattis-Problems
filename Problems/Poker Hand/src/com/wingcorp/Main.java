package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] hand = scanner.nextLine().split(" ");
        //A23456789TJQK
        int[] hist = new int[13];

        for (String card : hand){
            int index = rankToHist(card.charAt(0));
            hist[index]++;
        }

        int max = 0;

        for(int i : hist){
            if (i > max) max = i;
        }

        System.out.println(max);
    }

    public static int rankToHist(char c){
        switch (c){
            case 'A':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            case '9':
                return 8;
            case 'T':
                return 9;
            case 'J':
                return 10;
            case 'Q':
                return 11;
            case 'K':
                return 12;
            default:
                return -1;
        }
    }
}
