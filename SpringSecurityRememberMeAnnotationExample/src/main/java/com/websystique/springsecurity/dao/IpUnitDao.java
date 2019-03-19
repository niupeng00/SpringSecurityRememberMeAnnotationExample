package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.IpUnit;

import java.util.List;

public interface IpUnitDao {

    List<IpUnit> findByUnit();

    void addUnit(IpUnit ipUnit);
}
