package com.ethan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethan.dao.MemberMapper;
import com.ethan.entity.Member;
import com.ethan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2020/1/14.
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> selectAllMembers() {
        List<Member> members = memberMapper.selectAllMembers(new QueryWrapper<Member>());
        return members;
    }
}
