package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.IpWorkletter;

import java.util.List;

public interface IpWorkletterDao {

    List<IpWorkletter> findByWorkletter();

    void addWorkletter(IpWorkletter ipWorkletter);
}
