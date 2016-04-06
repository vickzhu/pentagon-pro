package com.pentagon.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.pentagon.system.dao.mapper.ResourcePermissionMapper;
import com.pentagon.system.dao.model.ResourcePermission;
import com.pentagon.system.dao.model.ResourcePermissionExample;
import com.pentagon.system.service.ResourcePermissionService;

@Service
public class ResourcePermissionServiceImpl extends BaseServiceImpl<ResourcePermission, ResourcePermissionExample> implements ResourcePermissionService {

    @Resource
    private ResourcePermissionMapper mapper;

    @Override
    protected BaseMapper<ResourcePermission, ResourcePermissionExample> getMapper() {
        return mapper;
    }

    @Override
    public void selectByPagination(ResourcePermissionExample example, Page<ResourcePermission> page) {
        example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<ResourcePermission> resourceList = selectByExample(example);
        page.setRecords(resourceList);
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.system.service.ResourcePermissionService#selectByGroup(java.lang.Long)
     */
    @Override
    public List<ResourcePermission> selectByGroup(Long groupId) {
        ResourcePermissionExample example = new ResourcePermissionExample();
        ResourcePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andResourceGroupIdEqualTo(groupId);
        return mapper.selectByExample(example);
    }

    /*
     * (non-Javadoc)
     * @see com.pentagon.system.service.ResourcePermissionService#existResource(java.lang.Long)
     */
    @Override
    public boolean existResource(Long groupId) {
        ResourcePermissionExample example = new ResourcePermissionExample();
        ResourcePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andResourceGroupIdEqualTo(groupId);
        return mapper.countByExample(example) > 0;
    }

}
