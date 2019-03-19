package com.websystique.springsecurity.dao.impl;

import com.websystique.springsecurity.dao.IpWorkletterDao;
import com.websystique.springsecurity.model.IpWorkletter;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IpWorkletterDaoImpl extends AbstractDao<String, IpWorkletter> implements IpWorkletterDao {

    @Override
    public List<IpWorkletter> findByWorkletter() {
        Criteria criteria = super.createEntityCriteria();
        return criteria.list();
    }

    @Override
    public void addWorkletter(IpWorkletter ipWorkletter) {
        super.save(ipWorkletter);
    }
}
