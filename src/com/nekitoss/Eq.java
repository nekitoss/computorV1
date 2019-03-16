package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eq {
    private String full_eq;


    private Pattern eqCheckPattern = Pattern.compile("=");
    private Pattern numbersAroundCheckPattern = Pattern.compile(".*\\d.*=.*\\d.*");


    public Eq(String full_eq) {
        this.full_eq = full_eq.replaceAll(" ", "");
    }

    public boolean checkEqualsSign(){
        Matcher mEq = eqCheckPattern.matcher(full_eq);
        int count = 0;

        while (mEq.find())
            count++;

        if (count == 0)
            Msg.errMsgExit("Equation has no \"=\"sign!");
        else if (count > 1)
            Msg.errMsgExit("Equation can have only one \"=\"sign!");
        else
        {
            Matcher mNum = numbersAroundCheckPattern.matcher(full_eq);
            if (!mNum.find())
                Msg.errMsgExit("Missing numbers around \"=\"");
            return true;
        }
        return false;
    }
}
