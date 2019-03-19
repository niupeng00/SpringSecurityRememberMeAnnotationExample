package com.websystique.springsecurity.dao.impl;

import com.websystique.springsecurity.dao.IpTechnologyDao;
import com.websystique.springsecurity.model.IpTechnology;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IpTechnologyDaoImpl extends AbstractDao<String, IpTechnology> implements IpTechnologyDao {


    @Override
    public List<IpTechnology> findByTechnology() {
        Criteria criteria = super.createEntityCriteria();
        List<IpTechnology> list = criteria.list();
        return criteria.list();
    }

    @Override
    public void addTechnology(IpTechnology ipTechnology) {
        super.save(ipTechnology);
    }
}
