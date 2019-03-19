package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.IpTechnology;
import java.util.List;

public interface IpTechnologyDao {

    List<IpTechnology> findByTechnology();

    void addTechnology(IpTechnology ipTechnology);
}
