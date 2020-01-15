package com.ethan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ethan.entity.Member;

import java.util.List;

/**
 * Created by ASUS on 2020/1/14.
 */
public interface MemberService extends IService<Member> {

    List<Member> selectAllMembers();
}
