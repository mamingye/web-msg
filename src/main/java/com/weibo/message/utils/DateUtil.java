package com.weibo.message.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by REDUDPY on 2018/4/19.
 */
public class DateUtil {
    public static  Date formatDate(Date date,String formatType){
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        try {
            if (date != null && StringUtils.isNotBlank(formatType)) {

                date =  format.parse(format.format(date));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return date;
    }
    public static Long getStampBYDate(Date date,String formatType){
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        try {
            if (date != null && StringUtils.isNotBlank(formatType)) {

                date =  format.parse(format.format(date));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return date.getTime();
    }
}


