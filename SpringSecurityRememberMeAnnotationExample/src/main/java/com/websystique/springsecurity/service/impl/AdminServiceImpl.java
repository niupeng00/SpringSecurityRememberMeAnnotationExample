package com.websystique.springsecurity.service.impl;

import com.websystique.springsecurity.dao.IpSupervisionDao;
import com.websystique.springsecurity.dao.IpTechnologyDao;
import com.websystique.springsecurity.dao.IpUnitDao;
import com.websystique.springsecurity.dao.IpWorkletterDao;
import com.websystique.springsecurity.model.IpSupervision;
import com.websystique.springsecurity.model.IpTechnology;
import com.websystique.springsecurity.model.IpUnit;
import com.websystique.springsecurity.model.IpWorkletter;
import com.websystique.springsecurity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    IpUnitDao ipUnitDao;
    @Autowired
    IpTechnologyDao ipTechnologyDao;
    @Autowired
    IpWorkletterDao ipWorkletterDao;
    @Autowired
    IpSupervisionDao ipSupervisionDao;

    @Override
    public List<IpUnit> findByUnit() {
        return ipUnitDao.findByUnit();
    }

    @Override
    public void addUnit(IpUnit ipUnit) {
        ipUnitDao.addUnit(ipUnit);
    }

    @Override
    public List<IpTechnology> findByTechnology() {
        return ipTechnologyDao.findByTechnology();
    }

    @Override
    public void addTechnology(IpTechnology ipTechnology) {
        ipTechnologyDao.addTechnology(ipTechnology);
    }

    @Override
    public List<IpWorkletter> findByWorkletter() {
        return ipWorkletterDao.findByWorkletter();
    }

    @Override
    public void addWorkletter(IpWorkletter ipWorkletter) {
        ipWorkletterDao.addWorkletter(ipWorkletter);
    }

    @Override
    public List<IpSupervision> findBySupervision() {
        return ipSupervisionDao.findBySupervision();
    }

    @Override
    public void addSupervision(IpSupervision ipSupervision) {
        ipSupervisionDao.addSupervision(ipSupervision);
    }

}
