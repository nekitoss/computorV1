package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */
public class Msg {
    public static void msgExit(String msg){
        System.out.println(msg);
        System.exit(1);
    }

    public static void errMsgExit(String msg){
        System.out.println("ERROR: "+msg);
        System.exit(1);
    }

    public static void msgResultExit(String msg, double i){
        System.out.println(msg + " " + i);
        System.exit(0);
    }
}
