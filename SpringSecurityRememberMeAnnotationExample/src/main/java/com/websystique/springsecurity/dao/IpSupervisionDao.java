package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.IpSupervision;

import java.util.List;

public interface IpSupervisionDao {

    List<IpSupervision> findBySupervision();

    void addSupervision(IpSupervision ipSupervision);
}
