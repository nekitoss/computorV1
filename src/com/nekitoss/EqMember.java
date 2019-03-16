package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */
class EqMember {
    private int pow;
    private double koef;

    EqMember(double koef, int pow) {
        this.pow = pow;
        this.koef = koef;
    }

    public int getPow() {
        return pow;
    }

    public double getKoef() {
        return koef;
    }
}
