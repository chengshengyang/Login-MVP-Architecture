package com.github.mvp.util;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

    public static final int TYPE_YEAR_MOUTH_DAY_TIME = 1;
    public static final int TYPE_MOUTH_DAY_TIME = 2;
    public static final int TYPE_YEAR_MOUTH_DAY = 3;
    public static final int TYPE_MOUTH_DAY = 4;
    public static final int TYPE_WEEK = 5;
    public static final int TYPE_MONTH = 6;
    public static final int TYPE_YEAR_MOUTH = 7;
    public static final int TYPE_AUTO = 8;//HH:mm:ss
    public static final int TYPE_AUTO_WEEK = 9;//今天||昨天||周几
    public static final int TYPE_AUTO_MOUTH = 10;//本月||5月||4月

    private static final int TYPE_TIME = 21;
    private static final int TYPE_TODAY = 22;
    private static final int TYPE_YESTERDAY = 23;

    private static String patternYear = "[/\\-.]yyyy|yyyy[/\\-.年]";
    private static String patternWeek = "[Ee] ,|, [Ee]|EEEE";// week格式

    /**
     * 返回格式化后的时间
     * @param context
     * @param when
     * @param type
     * @return
     */
    public static String format(Context context, long when, int type) {
        Format dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern();

        //删除星期
        Pattern patt = Pattern.compile(patternWeek);
        Matcher matt = patt.matcher(pattern);
        String patternResult = matt.replaceAll("");
        //删除空格
        String styleResult = patternResult.replace(" ", "");

        SimpleDateFormat formatter = null;

        Pattern pat = Pattern.compile(patternYear);
        Matcher mat = pat.matcher(styleResult);

        String time = " HH:mm:ss";
        String changePattern = "";
        String timeResult = "";

        Date whenDate = new Date(when);

        //自动转换
        if (type == TYPE_AUTO) {
            Date currDate = new Date(System.currentTimeMillis());
            if (DateUtils.isToday(when)) {
                type = TYPE_TIME;
            } else if (whenDate.getYear() != currDate.getYear()) {
                type = TYPE_YEAR_MOUTH_DAY_TIME;
            } else {
                type = TYPE_MOUTH_DAY_TIME;
            }
        } else if (type == TYPE_AUTO_WEEK) {//今天||昨天||周几
            long nowTime = System.currentTimeMillis();
            int passTime = (int) Math.floor((nowTime - when) / 1000);
            if (DateUtils.isToday(when)) {
                type = TYPE_TODAY;
            } else if (!DateUtils.isToday(when) && passTime < 86400) {
                type = TYPE_YESTERDAY;
            } else {
                type = TYPE_WEEK;
            }
        } else if (type == TYPE_AUTO_MOUTH) {
            Date currDate = new Date(System.currentTimeMillis());
            if (whenDate.getYear() != currDate.getYear()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(whenDate);
                timeResult = String.valueOf(cal.get(Calendar.YEAR)) + " 年" + String.valueOf(whenDate.getMonth() + 1) + " 月";
            } else if (whenDate.getYear() == currDate.getYear() && whenDate.getMonth() == currDate.getMonth()) {
                timeResult = "本月";
            } else {
                timeResult = String.valueOf(whenDate.getMonth() + 1) + " 月";
            }
        }

        switch (type) {
            case TYPE_YEAR_MOUTH_DAY:
                time = "";
            case TYPE_YEAR_MOUTH_DAY_TIME:
                if (mat.find()) {
                    changePattern = styleResult;
                } else {
                    changePattern = "yyyy-MM-dd";
                }
                changePattern += time;
                break;

            case TYPE_MOUTH_DAY_TIME:
                if (mat.find()) {
                    changePattern = mat.replaceAll("");
                } else {
                    changePattern = "MM-dd";
                }
                changePattern += time;
                break;

            case TYPE_MOUTH_DAY:
                if (mat.find()) {
                    changePattern = mat.replaceAll("");
                } else {
                    changePattern = "MM-dd";
                }
                break;
            case TYPE_YEAR_MOUTH:
                if (mat.find()) {
                    changePattern = mat.replaceAll("");
                } else {
                    changePattern = "yyyy-MM";
                }
                break;

            case TYPE_TIME:
                changePattern = "HH:mm:ss";
                break;

            case TYPE_WEEK:
                timeResult = getWeekOfDate(when);
                break;

            case TYPE_MONTH:
                changePattern = "MM";
                break;

            case TYPE_TODAY:
                timeResult = "今天";
                break;

            case TYPE_YESTERDAY:
                timeResult = "昨天";
                break;

            default:
                break;
        }

        if (!TextUtils.isEmpty(changePattern)) {
            formatter = new SimpleDateFormat(changePattern);
            timeResult = formatter.format(whenDate);
        }

        return timeResult;
    }

    /**
     * 返回当前时间是星期几
     * @param time
     * @return
     */
    public static String getWeekOfDate(long time) {
        Date date = new Date(time);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 微信朋友圈动态的发送时间显示方式
     * @param when
     * @return
     */
    public static String format(long when) {
        String timeResult = null;
        long nowTime = System.currentTimeMillis();
        int passTime = (int) Math.floor((nowTime - when) / 1000);//发送的时间和当前时间的间隔  单位：秒
        if (DateUtils.isToday(when)) {
            if (passTime < 60) {
                timeResult = "1分钟前";
            } else if (passTime > 60 && passTime < 3600) {
                timeResult = passTime / 60 + "分钟前";
            } else if (passTime >= 3600 && passTime < 86400) {
                timeResult = (int) Math.floor(passTime / (60 * 60)) + "小时前";
            }
        } else {
            if (passTime < 86400) {
                timeResult = "昨天";
            } else {
                timeResult = (int) Math.ceil((passTime / 86400)) + 1 + "天前";
            }
        }

        return timeResult;
    }
}