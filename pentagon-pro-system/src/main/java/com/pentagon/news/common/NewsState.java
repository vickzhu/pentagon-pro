/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.news.common;

/**
 * 类NewsState.java的描述：新闻状态
 * 
 * @author gandalf 2016年4月8日 下午4:18:35
 */
public enum NewsState {

    PENDING(1), APPROVED(2), UNAPPROVED(3);

    private int code;

    private NewsState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
