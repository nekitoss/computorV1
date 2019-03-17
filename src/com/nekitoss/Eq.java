package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */

import java.text.DecimalFormat;
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
            this.full_eq = full_eq.replaceAll("\\s", "");
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

    void outputShortForm() {
        DecimalFormat withoutPlusSign = new DecimalFormat("#.##;- #");
        DecimalFormat format = new DecimalFormat("+ #.##;- #");
        String outShort = String.format("%s * X^%d ", withoutPlusSign.format(koefArr[0]), 0);

        for (int i = 1; i <= maxPol; i++) {
            outShort += String.format("%s * X^%d ", format.format(koefArr[i]), i);
        }
        outShort += "= 0";
        outShort = outShort.replaceAll(",", ".");
        System.out.println("Reduced form: " + outShort);
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
                System.err.println("This is not a number!");
                System.exit(-1);
            }
            len += mAddition.group().length();
        }
        return len;
    }

    void outputPol() {
        System.out.println("Polynomial degree: " + maxPol);
        if (maxPol > 2) {
            Msg.msgExit("The polynomial degree is stricly greater than 2, I won't solve it, human! Solve yourself!");
        }
    }

    void trimZeroMembers() {
        int checkedDegree = 0;
        for (int i = this.maxPol; i >= 0; i--) {
            if (koefArr[i] != 0) {
                checkedDegree = i;
                break;
            }
        }
        if (checkedDegree != maxPol) {
            double newKoefMass[] = new double[checkedDegree + 1];
            for (int i = 0; i <= checkedDegree; i++) {
                newKoefMass[i] = koefArr[i];
            }
            this.koefArr = newKoefMass;
            this.maxPol = checkedDegree;

        }
    }

    void solve(){
        if (maxPol == 0) {
            if (koefArr[0] == 0)
                Msg.msgExit("The solution is: all real numbers");
            else
                Msg.msgExit("The solution is: empty set");
        }
        else if (maxPol == 1)
            Msg.msgResultExit("The solution is: ", (-1) * MyMath.div(koefArr[0], koefArr[1]));
        else
            solveDegree2();
    }

    private void solveDegree2() {
        double x1;
        double x2;
        double discriminator = 0;

        discriminator = koefArr[1] * koefArr[1] - 4.0 * koefArr[2] * koefArr[0];
        if (discriminator > 0) {
            x1 = ((-1.0) * koefArr[1] + MyMath.sqrt(discriminator)) / (2.0 * koefArr[2]);
            x2 = ((-1.0) * koefArr[1] - MyMath.sqrt(discriminator)) / (2.0 * koefArr[2]);
            Msg.msgExit("Discriminant is strictly positive, the two solutions are:\n" + x1 + "\n" + x2);
        } else if (discriminator == 0) {
            x1 = ((-1.0) * koefArr[1]) / (2.0 * koefArr[2]);
            Msg.msgExit("Discriminant is zero, the solution is:\n" + x1);
        } else {
            x1 = ((-1.0) * koefArr[1]) / (2.0 * koefArr[2]);
            x2 = MyMath.sqrt((-1.0) * discriminator) / (2.0 * koefArr[2]);
            Msg.msgExit("Discriminant is strictly negative, the two solutions are:\n" + x1 + " + " + x2 + "i\n" + x1 + " - " + x2 + "i");
        }
    }

}
