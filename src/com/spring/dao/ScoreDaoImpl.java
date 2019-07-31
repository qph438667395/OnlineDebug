package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.entity.Score;

@Repository("scoreDao")
public class ScoreDaoImpl extends BaseDaoImpl<Score> implements ScoreDao{

}
