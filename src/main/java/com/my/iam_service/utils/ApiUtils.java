package com.my.iam_service.utils;

import com.my.iam_service.model.constants.ApiConstants;

public class ApiUtils {

    public static String getMethodeName(){
        try{
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } catch (Exception cause){
            return ApiConstants.UNDEFINED;
        }
    }
}
