package com.ethan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethan.entity.Member;
import com.ethan.entity.Result;
import com.ethan.service.MemberService;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ASUS on 2020/1/14.
 */
@RestController
public class ServiceTestController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/getOne")
    public Result getOne() {
        Member member = memberService.getOne(new QueryWrapper<Member>().like("state", 1), false);
        return ResultUtil.success(member);
    }

    @RequestMapping("/selectMembers")
    public Result selectMembers() {
        List<Member> members = memberService.selectAllMembers();
        return ResultUtil.success(members);
    }

    @RequestMapping("/batch")
    public Result batch() {
        Member member = new Member();
        member.setName("李莉");
        member.setPassword("123131");

        Member member1 = new Member();
        member1.setName("李力");
        member1.setPassword("123131");

        List<Member> members = Arrays.asList(member, member1);

        boolean saveBatch = memberService.saveBatch(members);

        return ResultUtil.success(saveBatch);
    }

    @RequestMapping("/saveOrUpdateBatch")
    public Result saveOrUpdateBatch() {
        Member member = new Member();
        member.setName("李莉1");
        member.setPassword("123131");

        Member member1 = new Member();
        member1.setId(1216942918258937860L);
        member1.setName("李莉2");
        member1.setPassword("123167");

        List<Member> members = Arrays.asList(member, member1);

        boolean saveBatch = memberService.saveOrUpdateBatch(members);

        return ResultUtil.success(saveBatch);
    }

    @RequestMapping("/lambdaChain")
    public Result lambdaChain() {
        List<Member> memberList = memberService.lambdaQuery()
                .eq(Member::getState, 1)
                .like(Member::getSuperId, 1)
                .list();

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/lambdaChain1")
    public Result lambdaChain1() {
        boolean update = memberService.lambdaUpdate()
                .eq(Member::getName, "李莉1")
                .set(Member::getState, 1).set(Member::getSuperId, 1)
                .set(Member::getCreateTime, LocalDateTime.now())
                .update();

        return ResultUtil.success(update);
    }

    @RequestMapping("/lambdaChain2")
    public Result lambdaChain2() {
        boolean remove = memberService.lambdaUpdate()
                .eq(Member::getName, "李莉2")
                .remove();

        return ResultUtil.success(remove);
    }
}
