/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.common;

/**
 * 类NewsPriority.java的描述：
 * 
 * @author gandalf 2016年4月8日 下午4:22:35
 */
public enum NewsPriority {

    _1(1), _2(2), _3(3), _4(4), _5(5);

    private int code;

    private NewsPriority(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
