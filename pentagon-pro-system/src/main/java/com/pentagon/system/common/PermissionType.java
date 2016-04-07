/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.system.common;

/**
 * 类PermissionType.java的描述：权限类型
 * 
 * @author gandalf 2016年4月7日 下午12:34:56
 */
public enum PermissionType {

    RESOURCE(1), MENU(2);

    private int code;

    private PermissionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
