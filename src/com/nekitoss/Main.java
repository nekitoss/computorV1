package com.nekitoss;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        if (args.length == 1) {
            System.out.printf("Input: %s", args[0]);

        }
        else {
            System.out.println("Usage: write equation according to rules in \"\"");
            Msg.msgExit("Example: \"2*X^0= 1 * x ^ 0\"");
        }
    }
}
