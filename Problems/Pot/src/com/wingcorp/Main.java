package com.wingcorp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int N = Integer.parseInt(scanner.nextLine());
	    int acc = 0;

	    for(int i = 0; i < N; i++){
	        String line = scanner.nextLine();
	        int last = Character.digit(line.charAt(line.length() - 1),10);
	        int rest = Integer.parseInt(line.substring(0, line.length() - 1));
	        acc += Math.pow(rest, last);
        }

        System.out.println(acc);
    }
}
