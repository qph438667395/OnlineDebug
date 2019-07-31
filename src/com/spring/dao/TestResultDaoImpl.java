package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.TestResult;

@Repository("testResuleDao")
public class TestResultDaoImpl  extends BaseDaoImpl<TestResult> implements TestResultDao  {

}
