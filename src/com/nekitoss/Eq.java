package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eq {
    private String full_eq;
    private String lpart;
    private String rpart;

    private Pattern eqCheckPattern = Pattern.compile("=");
    private Pattern numbersAroundCheckPattern = Pattern.compile(".*\\d.*=.*\\d.*");
    private Pattern additionSubpartPattern = Pattern.compile("([-+]?\\d+\\.?\\d*)\\*[xX]\\^(\\d+)");//" *([-+]? *\\d+) *\\* *[xХ] *\\^ *(\\d)+ *"

    private ArrayList<String> lStrPartList = new ArrayList<>();
    private ArrayList<String> rStrPartList = new ArrayList<>();
    private ArrayList<EqMember> lMemberList = new ArrayList<>();
    private ArrayList<EqMember> rMemberList = new ArrayList<>();

    private int maxPol;
    private double koefArr[];

    Eq(String full_eq) {
        if (full_eq != null)
            this.full_eq = full_eq.replaceAll(" ", "");
        else
            Msg.errMsgExit("Passed equation as NUll String!");
    }

    void parseMemberList() {
        for (EqMember oneMember : this.lMemberList) {
            koefArr[oneMember.getPow()] += oneMember.getKoef();
        }
        for (EqMember oneMember : this.rMemberList) {
            koefArr[oneMember.getPow()] += (-1) * oneMember.getKoef();
        }
    }

    boolean checkEqualsSign(){
        Matcher mEq = eqCheckPattern.matcher(full_eq);
        int count = 0;

        while (mEq.find())
            count++;

        if (count == 0)
            Msg.errMsgExit("Equation has no \"=\" sign!");
        else if (count > 1)
            Msg.errMsgExit("Equation can have only one \"=\" sign!");
        else
        {
            Matcher mNum = numbersAroundCheckPattern.matcher(full_eq);
            if (!mNum.find())
                Msg.errMsgExit("Missing numbers around \"=\"");
            String [] tmp = full_eq.split("=");
            lpart = tmp[0];
            rpart = tmp[1];
            return true;
        }
        return false;
    }

    String getFull_eq() {
        return full_eq;
    }

    void lookupMaxPow(){
        int maxPow = 0;
        for (EqMember oneMember : this.lMemberList) {
            if (oneMember.getPow() > maxPow)
                maxPow = oneMember.getPow();
        }
        for (EqMember oneMember : this.rMemberList) {
            if (oneMember.getPow() > maxPow)
                maxPow = oneMember.getPow();
        }
        this.maxPol = maxPow;
        this.koefArr = new double[this.maxPol + 1];
    }

    int parseLeftPart() {
        return parsePart(lpart, lStrPartList, lMemberList);
    }

    int parseRightPart() {
        return parsePart(rpart, rStrPartList, rMemberList);
    }

    private int parsePart(String str, ArrayList<String> part, ArrayList<EqMember> eqPart) {
        Matcher mAddition = additionSubpartPattern.matcher(str);
        int len = 0;
        while (mAddition.find()) {
            part.add(mAddition.group());
            try {
                eqPart.add(new EqMember(Double.parseDouble(mAddition.group(1)), Integer.parseInt(mAddition.group(2))));
            } catch (NumberFormatException ex){
                System.err.println("This is not an int!");
                System.exit(-1);
            }
            len += mAddition.group().length();
        }
        return len;
    }

}
