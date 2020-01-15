package com.ethan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ethan.dao.MemberMapper;
import com.ethan.entity.Member;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/19.
 */
@RestController
public class DeleteMember {

    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("/deleteById")
    public Result deleteById(long id) {
        int row = memberMapper.deleteById(id);

        return ResultUtil.success(row);
    }

    @RequestMapping("/deleteByMap")
    public Result deleteByMap() {
        //map.put("name", "野九");
        //map.put("state", 1);
        //where name = "野九" and state = 1;

        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        map.put("state", 1);

        int rows = memberMapper.deleteByMap(map);

        return ResultUtil.success(rows);
    }

    @RequestMapping("/deleteByWrapper")
    public Result deleteByWrapper() {
        LambdaQueryWrapper<Member> lambdaQuery = new QueryWrapper<Member>().lambda();
        lambdaQuery.eq(Member::getName, "田七4").eq(Member::getState, 2);

        int rows = memberMapper.delete(lambdaQuery);

        return ResultUtil.success(rows);
    }
}
