package com.websystique.springsecurity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springsecurity.dto.LayuiResult;
import com.websystique.springsecurity.dto.Result;
import com.websystique.springsecurity.model.IpSupervision;
import com.websystique.springsecurity.model.IpTechnology;
import com.websystique.springsecurity.model.IpUnit;
import com.websystique.springsecurity.model.IpWorkletter;
import com.websystique.springsecurity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.interfaces.PBEKey;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }

    /**
     * 研究院
     * @return
     */
    @GetMapping(value = "unitTable")
    public String unitTable() {
        return "admin/unitTable";
    }

    @ResponseBody
    @GetMapping(value = "/unitJson")
    public String unitJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IpUnit> list = adminService.findByUnit();
        LayuiResult layuiResult = LayuiResult.success(list.size(), list);
        return objectMapper.writeValueAsString(layuiResult);
    }

    @GetMapping(value = "/unitGroup")
    public String unitGroup() {
        return "admin/unitGroup";
    }

    @ResponseBody
    @PostMapping(value = "/addUnit")
    public Result addUnit(IpUnit ipUnit) {
        adminService.addUnit(ipUnit);
        Result result = Result.success("保存成功！");
        return result;
    }

    /**
     * 科技厅
     * @return
     */
    @GetMapping(value = "/technologyTable")
    public String technologyTable() {
        return "admin/technologyTable";
    }

    @ResponseBody
    @GetMapping(value = "/technologyJson")
    public String technologyJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IpTechnology> list = adminService.findByTechnology();
        LayuiResult layuiResult = LayuiResult.success(list.size(), list);
        return objectMapper.writeValueAsString(layuiResult);
    }

    @GetMapping(value = "/technologyGroup")
    public String technologyGroup() {
        return "admin/technologyGroup";
    }

    @ResponseBody
    @PostMapping(value = "/addTechnology")
    public Result addTechnology(IpTechnology ipTechnology) {
        adminService.addTechnology(ipTechnology);
        Result result = Result.success("保存成功！");
        return result;
    }


    /**
     * 工信厅
     */
    @GetMapping(value = "/workletterTable")
    public String workletterTable() {
        return "admin/workletterTable";
    }

    @ResponseBody
    @GetMapping(value = "/workletterJson")
    public String workletterJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IpWorkletter> list = adminService.findByWorkletter();
        LayuiResult layuiResult = LayuiResult.success(list.size(), list);
        return objectMapper.writeValueAsString(layuiResult);
    }

    @GetMapping(value = "/workletterGroup")
    public String workletterGroup() {
        return "admin/workletterGroup";
    }

    @ResponseBody
    @PostMapping(value = "/addWorkletter")
    public Result addWorkletter(IpWorkletter ipWorkletter) {
        adminService.addWorkletter(ipWorkletter);
        Result result = Result.success("保存成功！");
        return result;
    }

    /**
     * 安监局
     */
    @GetMapping(value = "/supervisionTable")
    public String supervisionTable() {
        return "admin/supervisionTable";
    }

    @ResponseBody
    @GetMapping(value = "/supervisionJson")
    public String supervisionJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<IpSupervision> list = adminService.findBySupervision();
        LayuiResult layuiResult = LayuiResult.success(list.size(), list);
        return objectMapper.writeValueAsString(layuiResult);
    }

    @GetMapping(value = "/supervisionGroup")
    public String supervisionGroup() {
        return "admin/supervisionGroup";
    }

    @ResponseBody
    @PostMapping(value = "/addSupervision")
    public Result addSupervision(IpSupervision ipSupervision) {
        adminService.addSupervision(ipSupervision);
        Result result = Result.success("保存成功！");
        return result;
    }
}
