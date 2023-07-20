package com.coroutines_retrofit_mvvm.utils;

import android.content.Context;


public class Apphelper {

 //   private static CognitoUserPool userPool;

    private static String userPoolId = "", clientId = "", clientSecret = "";

 //   private static Regions cognitoRegion = null;

    public static void init(Context context) {
        userPoolId = "eu-west-1_X6Iwot4BP";
        clientId = "5n0ff7i9hdra3kghs6iogioc7";
        clientSecret = "12a565kkqjav5lkb8or0qg6o8f39nak0hiuh3q7924voj9fm20qm";
//        cognitoRegion = Regions.EU_WEST_1;
//        userPool = new CognitoUserPool(context, userPoolId, clientId, clientSecret, cognitoRegion);

    }

//    public static CognitoUserPool getPool() {
//        return userPool;
//    }

}
