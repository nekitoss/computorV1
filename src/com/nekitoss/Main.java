package com.nekitoss;

public class Main {

    public static void main(String[] args) {
        System.out.println();

        String[] test = new String[50];
        test[0] = "1 * X ^ 0 = 2 * X ^ 0";

        test[1] = "1 * x ^ 0 + 2 * X ^ 1 = - 1 * X ^ 0 + 4 * X ^ 1";
        test[2] = "-1 * x ^ 0 - 2 * X ^ 1 = 1 * X ^ 0 + 2 * X ^ 1";
        test[3] = "1 * x ^ 0 + 2 * X ^ 1 + 3 * X ^ 2 = - 1 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2";

        test[4] = "1 * X ^ 0 + 2 * X ^ 1 + 2 * X ^ 2 = - 1 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2";
        test[5] = "1 * X ^ 0 + 2 * X ^ 1 + 4 * X ^ 2 = - 1 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2";
        test[6] = "2 * X ^ 0 + 2 * X ^ 1 + 4 * X ^ 2 = - 1 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2";
        test[7] = "1 * X ^ 0 + 2 * X ^ 1 + 4 * X ^ 2 = 0 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2";
        test[8] = "1 * X ^ 0 + 2 * X ^ 1 + 4 * X ^ 2 = 0 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2 + 0 * X ^ 3 + 0 * X ^ 4 + 0 * X ^ 5";
        test[9] = "1 * X ^ 0 + 2.5 * X ^ 1 = - 1.561151 * X ^ 0 + 4.000 * X ^ 1";
        test[10] = "1.8526 * X ^ 0 + 2.989 * X ^ 1 + 2.16 * X ^ 2 = - 1.122241 * X ^ 0 + 4.999 * X ^ 1 + 3.25 * X ^ 2";

        test[11] = "1 * X ^ 0 = 2 * X ^ 0";
        test[12] = "1 * X ^ 0 = 1 * X ^ 0";
        test[13] = "1 * X ^ 0 + 2 * X ^ 1 + 4 * X ^ 2 = 0 * X ^ 0 + 4 * X ^ 1 + 3 * X ^ 2 + 0 * X ^ 3 + 0 * X ^ 4 + 2 * X ^ 5";
        test[14] = "0 = 1 * X ^ 0";
        test[15] = "1 * X ^ 0 = 0";
        test[16] = "0 = 0";

        test[17] = "5 * X ^ 0 = 5 * X ^ 0";
        test[18] = "4 * x ^ 0 = 8 * x ^ 0";
        test[19] = "5 * X ^ 0 = 4 * X ^ 0 + 7 * X ^ 1";
        test[20] = "5 * X ^ 0 + 13 * X ^ 1 + 3 * X ^ 2 = 1 * X ^ 0 + 1 * X ^ 1";
        test[21] = "5 * X ^ 0 + 13 * X ^ 1 + 3 * X ^ 2 = 1 * X ^ 0 + 1 * X ^ 1";

        test[22] = "6 * X ^ 0 + 11 * X ^ 1 + 5 * X ^ 2 = 1 * X ^ 0 + 1 * X ^ 1";
        test[23] = "5 * x ^ 0 + 3 * x ^ 1 + 3 * x ^ 2 = 1 * x ^ 0 + 0 * x ^ 1";
        test[24] = "5 * X ^ 0 + 3 * X ^ 1 + 3 * X ^ 2 = 1 * X ^ 0 + 0 * X ^ 1";

        test[25] = "";
        test[26] = "=3";


//        if (args.length == 1)
        for (int i = 0; i < test.length; i++)
        {
            args[0] = test[i];
            System.out.printf("T%d: ", i);
            Eq eq = new Eq(args[0]);
            if (eq.checkEqualsSign()) {
                if ((eq.parseLeftPart() + eq.parseRightPart() + 1) != eq.getFull_eq().length())
                {
                    Msg.errMsgExit("not all part can be recognized");
                }
                eq.lookupMaxPow();
                eq.parseMemberList();
                eq.trimZeroMembers();
                eq.outputShortForm();


            }
        }
//        else {
//            System.out.println("Usage: write equation according to rules in \"\"");
//            Msg.msgExit("Example: \"2*X^0= 1 * x ^ 0\"");
//        }
    }
}
