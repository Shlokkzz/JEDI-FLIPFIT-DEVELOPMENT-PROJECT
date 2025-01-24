package com.flipkart.utils;

import static com.flipkart.utils.dbutils.getTableCnt;

public class sharedState {
    private static int cntUsers = getTableCnt("user");
    private static int cntCenters = getTableCnt("gym_center");
    private static int cntCity = getTableCnt("city");
    private static int cntBookings = getTableCnt("booking");
    private static int cntSlot = getTableCnt("slot");

    public static int getCntUsers() { 
        return cntUsers;
    }

    public static void incrementCntUsers() {
        cntUsers++;
    }

    public static int getCntCenters() {
        return cntCenters;
    }

    public static void incrementCntCenters() {
        cntCenters++;
    }

    public static int getCntCity() {
        return cntCity;
    }

    public static void incrementCntCity() {
        cntCity++;
    }

    public static int getCntBookings() { return cntBookings; }

    public static void incrementCntBookings() {cntBookings++; }

    public static int getCntSlot() {return cntSlot;}

    public static void incrementCntSlot() {cntCity++;}
}
