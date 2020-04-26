package com.sxh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息处理工具类
 * @author sxh
 * @date 2020/4/26
 */
public class MessageUtils {
    public static String encodeMsg (Message message) {
        return message.getUsername() + "~" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message.getSentTime())) + "~" + message.getMsg();
    }

    public static String formatDateTime(Date time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static Date parseDateTime(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 打印消息
     * @param msg
     */
    public static void printMsg(Message msg) {
        System.out.println("=================================================================================================");
        System.out.println("                      " + MessageUtils.formatDateTime(msg.getSentTime()) + "                     ");
        System.out.println(msg.getUsername() + ": " + msg.getMsg());
        System.out.println("=================================================================================================");

    }
}
