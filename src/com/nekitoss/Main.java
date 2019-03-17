package com.nekitoss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Pattern numbersAroundCheckPattern = Pattern.compile("((?:^|[-+]{1})\\d+\\.?\\d*)(?:\\*[xX]\\^([-+]?\\d+))?");//((?:[^|[-+]{1}])\d+\.?\d*)(?:\*[xX]\^([-+]?\d+))?
        String tst = "5+17x^1";
        String tst1 = "+5";
        String tst2 = "-5";
        Matcher m = numbersAroundCheckPattern.matcher(tst);
        Matcher m1 = numbersAroundCheckPattern.matcher(tst1);
        Matcher m2 = numbersAroundCheckPattern.matcher(tst2);

        int i = 1;
        while (m.find())
        {
            System.out.println(i+"="+m.group());
            i++;
        }
        System.out.println("\nm.find() = " + m.find());
        System.out.println("m1.find() = " + m1.find());
        System.out.println("m2.find() = " + m2.find());

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
