package com.pentagon.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.pentagon.system.dao.mapper.MenuPermissionMapper;
import com.pentagon.system.dao.model.MenuPermission;
import com.pentagon.system.dao.model.MenuPermissionExample;
import com.pentagon.system.service.MenuPermissionService;

@Service
public class MenuPermissionServiceImpl extends BaseServiceImpl<MenuPermission, MenuPermissionExample> implements MenuPermissionService {

    @Resource
    private MenuPermissionMapper mapper;
    
    @Override
    protected BaseMapper<MenuPermission, MenuPermissionExample> getMapper() {
        return mapper;
    }

}
