package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.TestCase;

@Repository("testCaseDao")
public class TestCaseDaoImpl  extends BaseDaoImpl<TestCase> implements TestCaseDao  {

}
