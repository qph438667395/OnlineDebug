package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.Question;

@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao{

}
