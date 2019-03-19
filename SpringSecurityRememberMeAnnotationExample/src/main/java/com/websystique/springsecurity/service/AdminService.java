package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dto.Result;
import com.websystique.springsecurity.model.IpSupervision;
import com.websystique.springsecurity.model.IpTechnology;
import com.websystique.springsecurity.model.IpUnit;
import com.websystique.springsecurity.model.IpWorkletter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AdminService {

    /**
     * 查询研究院IP
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<IpUnit> findByUnit();

    @PreAuthorize("hasRole('ADMIN')")
    void addUnit(IpUnit ipUnit);

    /**
     * 查询科技厅IP
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<IpTechnology> findByTechnology();

    @PreAuthorize("hasRole('ADMIN')")
    void addTechnology(IpTechnology ipTechnology);

    /**
     * 查询工信厅 IP
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<IpWorkletter> findByWorkletter();

    @PreAuthorize("hasRole('ADMIN')")
    void addWorkletter(IpWorkletter ipWorkletter);

    /**
     * 查询安监 IP
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<IpSupervision> findBySupervision();

    @PreAuthorize("hasRole('ADMIN')")
    void addSupervision(IpSupervision ipSupervision);





    /*@PreAuthorize("hasRole('ADMIN')")
    Result IpSave(IpAddress ipAddress);

    @PreAuthorize("hasRole('ADMIN')")
    List<IpAddress> find();*/
}
