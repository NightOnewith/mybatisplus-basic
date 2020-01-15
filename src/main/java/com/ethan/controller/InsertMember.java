package com.ethan.controller;

import com.ethan.dao.MemberMapper;
import com.ethan.entity.Member;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by ASUS on 2019/12/19.
 */
@RestController
public class InsertMember {

    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("/insertOne")
    public Result insertOne() {
        Member member = new Member();
        //member.setId(1123454332234L);
        member.setName("阿萨德");
        member.setPassword("123412");
        member.setState(1);
        member.setSuperId(1L);
        member.setCreateTime(LocalDateTime.now());
        memberMapper.insert(member);

        return ResultUtil.success(member);
    }
}
