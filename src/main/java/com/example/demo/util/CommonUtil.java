package com.example.demo.util;

import java.util.UUID;

public class CommonUtil {

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        String uuid = getUUID().substring(0,number);
        return uuid;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static String getPrimaryKey(){
        return getUUID(16);
    }
}
