package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

}
