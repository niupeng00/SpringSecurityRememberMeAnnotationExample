package com.websystique.springsecurity.dao.impl;

import com.websystique.springsecurity.dao.IpUnitDao;
import com.websystique.springsecurity.model.IpUnit;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IpUnitDaoImpl extends AbstractDao<String, IpUnit> implements IpUnitDao {
    @Override
    public List<IpUnit> findByUnit() {
        Criteria criteria = super.createEntityCriteria();
        return criteria.list();
    }

    @Override
    public void addUnit(IpUnit ipUnit) {
        super.save(ipUnit);
    }
}
