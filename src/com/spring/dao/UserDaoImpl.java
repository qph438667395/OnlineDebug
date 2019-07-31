package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
