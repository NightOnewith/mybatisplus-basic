package com.ethan.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ethan.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ASUS on 2019/12/19.
 */
public interface MemberMapper extends BaseMapper<Member> {

    //  自定义SQL语句接口
    //@Select("select * from sys_member ${ew.customSqlSegment}")
    List<Member> selectAllMembers(@Param(Constants.WRAPPER) Wrapper<Member> wrapper);

    //  自定义分页查询接口
    IPage<Member> selectMemberPage(IPage<Member> page, @Param(Constants.WRAPPER) Wrapper<Member> wrapper);
}
