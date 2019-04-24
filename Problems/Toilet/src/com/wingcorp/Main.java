package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] parts = scanner.nextLine().toCharArray();

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        char current1 = parts[0];
        char current2 = parts[0];
        char current3 = parts[0];

        for(int i = 1; i < parts.length; i++) {
            if (current1 != parts[i]) p1++;
            if (current2 != parts[i]) p2++;
            if (current3 != parts[i]) p3++;

            current1 = parts[i];
            current2 = parts[i];
            current3 = parts[i];

            if(current1 == 'D'){
                p1++;
                current1 = 'U';
            }
            if(current2 == 'U'){
                p2++;
                current2 = 'D';
            }
        }

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}
