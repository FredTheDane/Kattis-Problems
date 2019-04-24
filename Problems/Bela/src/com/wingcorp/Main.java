package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int result = 0;
        int hands = Integer.parseInt(params[0]);
        char dominant = params[1].charAt(0);

        for(int i = 0; i < (hands * 4); i++){
            String line = scanner.nextLine();
            result += getPoints(line, dominant);
        }

        System.out.println(result);
    }

    public static int getPoints(String seq, char dominant){
        char[] seqParts = seq.toCharArray();
        switch (seqParts[0]){
            case 'A':
                return 11;
            case 'K':
                return 4;
            case 'Q':
                return 3;
            case 'J':
                if (seqParts[1] == dominant) return 20;
                return 2;
            case 'T':
                return 10;
            case '9':
                if (seqParts[1] == dominant) return 14;
                return 0;
            default:
                return 0;
        }
    }
}
