package com.lwcd.utilslibrary;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Create by Linw
 * on 2018/12/7
 */
public class CustomTextUtils {

    /**
     * 截取小数点后几位，不足返回原值
     * @param value
     * @param limitLenth
     * @return
     */
    public static double cutValue(double value, int limitLenth) {
        String valueStr = String.valueOf(value);

        // 判断是否科学计算法字符串并转化
        if (valueStr.indexOf("E") > 0 || valueStr.indexOf("e") > 0) {
            BigDecimal bd = new BigDecimal(valueStr);
            valueStr = bd.toPlainString();
        }

        int pointIndex = valueStr.indexOf(".");
        if (pointIndex != -1) { // 有小数点
            int outLength = valueStr.length() - 1 - pointIndex;
            if (outLength > limitLenth) {
                valueStr = valueStr.substring(0, pointIndex + limitLenth + 1);
            }
        }

        return Double.valueOf(valueStr);
    }

    /**
     * UTC转本地时间
     * @param utcTimeStr
     * @return
     */
    public static String utc2LocalTime(String utcTimeStr) {
        if (!(utcTimeStr.contains("T") && utcTimeStr.contains("Z"))) return utcTimeStr;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        //设置时区UTC
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        //格式化，转当地时区时间
        Date after = null;
        try {
            after = df.parse(utcTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTimeStr;
        }
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        //默认时区
        df.setTimeZone(TimeZone.getDefault());
        return df.format(after);
    }
}
