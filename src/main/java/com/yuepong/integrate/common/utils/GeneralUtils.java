package com.yuepong.integrate.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GeneralUtil
 * @Description 通用工具类
 * @Author lixuefei
 * @Date 2021.7.23 16:42
 **/
@Component("GeneralUtils")
public class GeneralUtils {
    
    /**
     * String转Date
     *
     * @param date:字符串日期，字符串日期格式必须为 yyyy-MM-dd HH:mm:ss
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDate(String date) throws ParseException {
        if (date != null && date.length() != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        } else {
            return null;
        }
    }
    
    
    /**
     * String转Date
     *
     * @param date:字符串日期
     * @param format:字符串的日期格式，默认为yyyy-MM-dd HH:mm:ss
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDate(String date, String format) throws ParseException {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date != null && date.length() != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } else {
            return null;
        }
    }
    
    
    /**
     * 判断两个字符串的相似度(实验)
     *
     * @param strA:字符串A
     * @param strB:字符串B
     * @return float:相似度
     */
    public static float stringCalculateSimilarity(String strA, String strB) {
        int d[][]; // 矩阵
        int n = strA.length();
        int m = strB.length();
        int i; // 遍历strA的
        int j; // 遍历strB的
        char ch1; // strA的
        char ch2; // strB的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }
        
        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }
        
        for (i = 1; i <= n; i++) { // 遍历strA
            ch1 = strA.charAt(i - 1);
            // 去匹配strB
            for (j = 1; j <= m; j++) {
                ch2 = strB.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }
        return (1 - (float) d[n][m] / Math.max(strA.length(), strB.length())) * 100F;
    }
    
    
    /**
     * 当BigDecimal为空时，赋值为0
     *
     * @param amount:BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal bigDecimalReducedZero(BigDecimal amount) {
        return amount == null ? new BigDecimal(BigInteger.ZERO) : amount;
    }
    
    
    /**
     * 金额转中文大写
     *
     * @param amount:BigDecimal
     * @return 中文金额
     */
    public static String bigDecimalAmountCapital(BigDecimal amount) {
        if (amount == null) {
            amount = bigDecimalReducedZero(amount);
        }
        String[] chinese = new String[]{"", "拾 ", "佰 ", "仟 ", "万 ", "拾 ", "佰 ", "仟 ", "亿 ", "拾 ", "佰 ", "仟 ", "万 "};
        String[] numChinese = new String[]{"零 ", "壹 ", "贰 ", "叁 ", "肆 ", "伍 ", "陆 ", "柒 ", "捌 ", "玖 "};
        String[] afterChinese = new String[]{"角 ", "分 "};
        String str = String.valueOf(amount);
        if (str.contains("-")) {
            str = str.replace("-", "");
        }
        String[] arr = str.split("\\.");
        char[] chars = arr[0].toCharArray();
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < chars.length; ++i) {
            sb.append(numChinese[Integer.parseInt(String.valueOf(chars[i]))]).append(chinese[chars.length - i - 1]);
        }
        
        if (arr.length == 1) {
            return sb.toString() + "元 整";
        } else if (arr[1].length() > 2) {
            throw new IllegalArgumentException("人民币大写转换BigDecimal只能保留2位小数");
        } else {
            sb.append("元 ");
            char[] chars1 = arr[1].toCharArray();
            
            for (int i = 0; i < chars1.length; ++i) {
                sb.append(numChinese[Integer.parseInt(String.valueOf(chars1[i]))]).append(afterChinese[i]);
            }
            
            return sb.toString();
        }
    }
    
    
    /**
     * Date转String
     *
     * @param date 日期
     * @return String
     */
    public static String dateToString(Date date) {
        if (date != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return format.format(date);
        } else {
            return null;
        }
    }
    
    
    /**
     * Date转String
     *
     * @param date   日期
     * @param format 转化字符串的日期格式，默认为 yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date != null) {
            if (format == null) {
                format = "yyyy-MM-dd HH:mm:ss.SSS";
            }
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } else {
            return null;
        }
    }
    
    
    /**
     * Date转Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp dateToTimestamp(Date date) throws ParseException {
        if (date != null) {
            return new Timestamp(date.getTime());
        } else {
            return null;
        }
    }
    
    public static Date overtimeDate(Date date, Long time) {
        return new Date(date.getTime() + time);
    }
    
    
    /**
     * 计算两个时间之间的间隔
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return timeDifference 时间差
     */
    public static String timeDifference(Long start, Long end) {
        String timeDifference = "";
        if (start != null && end != null) {
            if (start <= end) {
                long between = end - start;
                long day = between / (24 * 60 * 60 * 1000);
                String D = day != 0 ? (day + " 天 ") : "";
                long hour = (between / (60 * 60 * 1000) - day * 24);
                String H = hour != 0 ? (hour + " 小时 ") : "";
                long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
                String M = min != 0 ? (min + " 分钟 ") : "";
                long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                String S = s != 0 ? (s + " 秒 ") : "";
                long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                        - min * 60 * 1000 - s * 1000);
                String Ms = ms != 0 ? (ms + " 毫秒") : "";
                timeDifference = "".equals(D + H + M + S + Ms) ? "0 毫秒" : (D + H + M + S + Ms);
            } else {
                timeDifference = "小于 100 毫秒";
            }
        } else {
            if (start == null) {
                timeDifference = timeDifference.concat("开始时间缺失，");
            }
            if (end == null) {
                timeDifference = timeDifference.concat("结束时间缺失，");
            }
            timeDifference = timeDifference.concat("计算失败");
        }
        return timeDifference;
    }
    
    /**
     * 计算两个时间之间的间隔
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return timeDifference 时间差
     */
    public static String timeDifference(Date start, Date end) {
        String timeDifference = "";
        if (start != null && end != null) {
            long startTime = start.getTime();
            long endTime = end.getTime();
            if (startTime <= endTime) {
                long between = endTime - startTime;
                long day = between / (24 * 60 * 60 * 1000);
                String D = day != 0 ? (day + " 天 ") : "";
                long hour = (between / (60 * 60 * 1000) - day * 24);
                String H = hour != 0 ? (hour + " 小时 ") : "";
                long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
                String M = min != 0 ? (min + " 分钟 ") : "";
                long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                String S = s != 0 ? (s + " 秒 ") : "";
                long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                        - min * 60 * 1000 - s * 1000);
                String Ms = ms != 0 ? (ms + " 毫秒") : "";
                timeDifference = "".equals(D + H + M + S + Ms) ? "0 毫秒" : (D + H + M + S + Ms);
            } else {
                timeDifference = "小于 100 毫秒";
            }
        } else {
            if (start == null) {
                timeDifference = timeDifference.concat("开始时间缺失，");
            }
            if (end == null) {
                timeDifference = timeDifference.concat("结束时间缺失，");
            }
            timeDifference = timeDifference.concat("计算失败");
        }
        return timeDifference;
    }
    
    
    private static Map<String, Object> getMap(String param) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        String key = "";
        Object value = "";
        char[] charList = param.replace(", ", ",").toCharArray();
        boolean valueBegin = false;
        for (int i = 0; i < charList.length; i++) {
            char c = charList[i];
            if (c == '{') {
                if (valueBegin) {
                    value = getMap(param.substring(i, param.length()));
                    i = param.indexOf('}', i) + 1;
                    map.put(key, value);
                }
            } else if (c == '=') {
                valueBegin = true;
                key = str.toString();
                str = new StringBuilder();
            } else if (c == ',') {
                valueBegin = false;
                value = str.toString();
                str = new StringBuilder();
                map.put(key, value);
            } else if (c == '}') {
                if (!"".equals(str.toString())) {
                    value = str.toString();
                }
                map.put(key, value);
                return map;
            } else {
                str.append(c);
            }
        }
        return map;
    }
    
    
    private static boolean isJson(String str) {
        try {
            Object obj = JSON.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
