package com.pentagon.system.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.util.Assert;
import com.pentagon.system.dao.model.Department;
import com.pentagon.system.dao.model.DepartmentExample;
import com.pentagon.system.service.DepartmentService;
import com.pentagon.system.test.BaseTest;

public class DepartmentServiceTest extends BaseTest {
	
	@Resource
	private DepartmentService deptService;
	
	@Test
	public void selectByExampleTest(){
		DepartmentExample deptExample = new DepartmentExample();
		deptExample.setOffset(0);
		deptExample.setRows(1);
		List<Department> deptList = deptService.selectByExample(deptExample);
		Assert.assertNotNull(deptList);
	}
	
}
