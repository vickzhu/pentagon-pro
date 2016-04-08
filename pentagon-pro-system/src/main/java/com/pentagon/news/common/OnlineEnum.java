/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 类OnlineEnum.java的描述：是否在线
 * 
 * @author gandalf 2016年4月8日 下午4:21:00
 */
public enum OnlineEnum {

    YES(1, "是"), NO(2, "否");

    private int                         code;
    private String                      desc;
    private static Map<Integer, String> onlineMap = new HashMap<Integer, String>();

    static {
        for (OnlineEnum online : OnlineEnum.values()) {
            onlineMap.put(online.getCode(), online.getDesc());
        }
    }

    private OnlineEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(int code) {
        return onlineMap.get(code);
    }

}
