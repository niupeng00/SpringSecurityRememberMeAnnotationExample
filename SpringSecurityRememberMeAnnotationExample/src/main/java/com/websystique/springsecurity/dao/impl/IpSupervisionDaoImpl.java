package com.websystique.springsecurity.dao.impl;

import com.websystique.springsecurity.dao.IpSupervisionDao;
import com.websystique.springsecurity.model.IpSupervision;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IpSupervisionDaoImpl extends AbstractDao<String, IpSupervision> implements IpSupervisionDao {

    @Override
    public List<IpSupervision> findBySupervision() {
        Criteria criteria = super.createEntityCriteria();
        return criteria.list();
    }

    @Override
    public void addSupervision(IpSupervision ipSupervision) {
        super.save(ipSupervision);
    }
}
