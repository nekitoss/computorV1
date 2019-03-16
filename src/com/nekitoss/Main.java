package com.nekitoss;

public class Main {

    public static void main(String[] args) {
        System.out.println();

        if (args.length == 1)
        {
            Eq eq = new Eq(args[0]);
            if (eq.checkEqualsSign()) {
                if ((eq.parseLeftPart() + eq.parseRightPart() + 1) != eq.getFull_eq().length())
                {
                    Msg.errMsgExit("Syntax errors");
                }
                eq.lookupMaxPow();
                eq.parseMemberList();
                eq.trimZeroMembers();
                eq.outputShortForm();
                eq.outputPol();
                eq.solve();
            }
        }
        else {
            Msg.msgExit("Usage: write equation according to rules in \" \"\nExample: \"2*X^2= 1 * X ^ 0\"");
        }
    }
}
