/*
 * Copyright 2010-2016 gandalf All right reserved. This software is the confidential and proprietary information of
 * gandalf ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with gandalf.
 */
package com.pentagon.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.common.PermissionType;
import com.pentagon.system.dao.mapper.RolePermissionMapper;
import com.pentagon.system.dao.model.RolePermission;
import com.pentagon.system.dao.model.RolePermissionExample;
import com.pentagon.system.service.RolePermissionService;

/**
 * 类RolePermissionServiceImpl.java的描述：角色权限service实现类
 * 
 * @author gandalf 2016年4月7日 下午12:37:32
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, RolePermissionExample> implements RolePermissionService {

    @Resource
    private RolePermissionMapper mapper;

    /*
     * (non-Javadoc)
     * @see com.gandalf.framework.mybatis.BaseServiceImpl#getMapper()
     */
    @Override
    protected BaseMapper<RolePermission, RolePermissionExample> getMapper() {
        return mapper;
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.system.service.RolePermissionService#selectByRole(java.lang.Long)
     */
    @Override
    public List<RolePermission> selectByRole(Long roleId) {
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria critera = example.createCriteria();
        critera.andRoleIdEqualTo(roleId);
        return mapper.selectByExample(example);
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.system.service.RolePermissionService#selectByPermissionType(java.lang.Long,
     * com.pentagon.system.common.PermissionType)
     */
    @Override
    public RolePermission selectByPermissionTypeWithBlobs(Long roleId, PermissionType permissionType) {
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria critera = example.createCriteria();
        critera.andRoleIdEqualTo(roleId);
        critera.andPermissionTypeEqualTo(permissionType.getCode());
        List<RolePermission> list = mapper.selectByExampleWithBLOBs(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

}
