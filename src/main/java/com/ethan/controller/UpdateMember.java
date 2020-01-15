package com.ethan.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.ethan.dao.MemberMapper;
import com.ethan.entity.Member;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/19.
 */
@RestController
public class UpdateMember {

    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("/updateById")
    public Result updateById(long id) {
        Member member = new Member();
        member.setId(id);
        member.setCreateTime(LocalDateTime.now());
        //  根据ID修改对象，updateById方法中传入的是实体类，实体类中的ID必须有，其他字段的set值为修改后的值
        int rows = memberMapper.updateById(member);

        Member selectById = memberMapper.selectById(id);

        return ResultUtil.success(selectById);
    }

    @RequestMapping("/updateByWrapper")
    public Result updateByWrapper() {
        //  set语句
        Member member = new Member();
        member.setPassword("54321");
        member.setSuperId(2L);
        member.setCreateTime(LocalDateTime.now());

        //  where条件
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "野九").eq("state", 1);

        //  更新操作
        int rows = memberMapper.update(member, updateWrapper);

        //  查询更新后的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/updateByWrapper1")
    public Result updateByWrapper1() {
        //  set语句
        Member member = new Member();
        member.setPassword("54321");
        member.setSuperId(2L);
        member.setCreateTime(LocalDateTime.now());

        //  where条件
        Member whereMember = new Member();
        member.setName("野九");
        member.setState(1);
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>(whereMember);

        //  更新操作
        int rows = memberMapper.update(member, updateWrapper);

        //  查询更新后的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/updateByWrapper2")
    public Result updateByWrapper2() {
        //  使用UpdateWrapper的链式调用方法将where和set条件直接设置到UpdateWrapper构造器中
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "野九").eq("state", 1).set("create_time", LocalDateTime.now());

        //  更新操作
        int rows = memberMapper.update(null, updateWrapper);

        //  查询更新后的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/updateByWrapperLambda")
    public Result updateByWrapperLambda() {
        LambdaUpdateWrapper<Member> lambdaUpdateWrapper = new UpdateWrapper<Member>().lambda();
        lambdaUpdateWrapper.eq(Member::getName, "野九").eq(Member::getState, 1).set(Member::getCreateTime, LocalDateTime.now());

        //  更新操作
        int row = memberMapper.update(null, lambdaUpdateWrapper);

        //  查询更新后的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/updateByWrapperLambdaChain")
    public Result updateByWrapperLambdaChain() {
        boolean update = new LambdaUpdateChainWrapper<Member>(memberMapper)
                .eq(Member::getName, "野九").eq(Member::getState, 1).set(Member::getCreateTime, LocalDateTime.now())
                .update();

        //  查询更新后的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "野九");
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

}
