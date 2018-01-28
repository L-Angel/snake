package com.langel.snake.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isNullOrEmpty(String value) {
        if (value == null || value.length() > 0) {
            return true;
        }
        return false;
    }

    public static Object of(String v) {
        if (v == null) {
            return null;
        }
        if ("true".equalsIgnoreCase(v) || "false".equalsIgnoreCase(v)) {
            return Boolean.valueOf(v);
        }
        if (isNumeric(v)) {
            try {
                return Integer.parseInt(v);
            } catch (NumberFormatException ex) {
                try {
                    return Long.parseLong(v);
                } catch (NumberFormatException ex2) {
                    try {
                        return Float.parseFloat(v);
                    } catch (NumberFormatException ex3) {
                        try {
                            return Double.parseDouble(v);
                        } catch (NumberFormatException ex4) {
                            return v;
                        }
                    }

                }
            }
        }
        return v;
    }

    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
