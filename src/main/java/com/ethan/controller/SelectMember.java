package com.ethan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.dao.MemberMapper;
import com.ethan.entity.Member;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by ASUS on 2019/12/19.
 */
@RestController
public class SelectMember {

    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("/selectAll")
    public Result selectAll() {
        List<Member> members = memberMapper.selectList(null);
        members.forEach(System.out::println);

        return ResultUtil.success(members);
    }

    @RequestMapping("/selectById")
    public Result selectById(long id) {
        Member member = memberMapper.selectById(id);

        return ResultUtil.success(member);
    }

    @RequestMapping("/selectByIds")
    public Result selectByIds() {
        List<Integer> idList = Arrays.asList(1, 2, 3, 4, 5);
        List memberList = memberMapper.selectBatchIds(idList);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByMap")
    public Result selectByMap() {
        //map.put("name", "田七");
        //map.put("password", 1234);
        //where name = "田七" and password = 1234;

        Map<String, Object> map = new HashMap<>();
        map.put("name", "田七");
        map.put("password", 1234);
        List<Member> memberList = memberMapper.selectByMap(map);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapper")
    public Result selectByWrapper() {
        /*查询name中包含“三”，且state小于2的成员
        where name like '%三%' and state < 2*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","三").lt("state", 2);

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapper2")
    public Result selectByWrapper2() {
        /*查询name中包含“田”，state大于0且小于2，且password不为空的成员
        where name like '%田%' and state between 0 and 2 and password is not null*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","田").between("state", 0, 2).isNotNull("password");

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapper3")
    public Result selectByWrapper3() {
        /*查询name中姓“田”或者state大于等于2，按state降序排列，state相同按id升序排列
        where name like '田%' or state >= 2 order by state desc,id asc*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name","田").or().ge("state",2).orderByDesc("state").orderByAsc("id");

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapperSupper")
    public Result selectByWrapperSupper() {
        /*查询name中包含“三”，且state小于2的成员，只列出id，name
        where name like '%三%' and state < 2*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name","三").lt("state", 2);

        List<Member> memberList = memberMapper.selectList(queryWrapper);
        memberList.forEach(System.out::println);
        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapperSupper2")
    public Result selectByWrapperSupper2() {
        /*查询name中包含“三”，且state小于2的成员，不列出super_id和create_time
        where name like '%三%' and state < 2*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","三").lt("state", 2)
                    .select(Member.class, info->!info.getColumn().equals("super_id")&&
                    !info.getColumn().equals("create_time"));

        List<Member> memberList = memberMapper.selectList(queryWrapper);
        memberList.forEach(System.out::println);
        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByCondition")
    public Result selectByCondition() {
        /*有些场景需求为，前台提供用户使用id或者name进行查询，
        下面演示以前的查询方法和MP的查询方法condition的使用*/

        long id = 0;
        String name = "";
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();

        //  以前的查询方法
        /*if(StringUtils.isNotEmpty(String.valueOf(id))) {
            queryWrapper.like("id", id);
        }
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }*/

        //MP中condition的使用
        queryWrapper.like(StringUtils.isNotEmpty(String.valueOf(id)), "id", id)
                    .like(StringUtils.isNotEmpty(name), "name", name);

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapperEntity")
    public Result selectByWrapperEntity() {
        /*查询name=“田七3”，且state等于2的成员
        where name = “田七3” and state = 2*/

        Member member = new Member();
        member.setName("田七3");
        member.setState(2);

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(member);

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapperAllEq")
    public Result selectByWrapperAllEq() {
        /*查询name=“田七3”，且password等于12343的成员
        where name = “田七3” and password = 12343*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "田七3");
        params.put("password", null);
        queryWrapper.allEq(params, false);

        List<Member> memberList = memberMapper.selectList(queryWrapper);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByWrapperMaps")
    public Result selectByWrapperMaps() {
        /*查询name中包含“三”，且state小于2的成员，只列出id，name
        where name like '%三%' and state < 2*/

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name","三").lt("state", 2);

        List<Map<String, Object>> memberList = memberMapper.selectMaps(queryWrapper);
        //List<Member> memberList = memberMapper.selectList(queryWrapper);
        memberList.forEach(System.out::println);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectLambda")
    public Result selectLambda() {
        /*查询name中包含“田”，且state小于2的成员
        where name like '%田%' and state < 2*/

        LambdaQueryWrapper<Member> lambdaQuery = new QueryWrapper<Member>().lambda();
        lambdaQuery.like(Member::getName, "田").lt(Member::getState, 2);

        List<Member> memberList = memberMapper.selectList(lambdaQuery);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectAllMembers")
    public Result selectAllMembers() {
        /*查询name中包含“田”，且state小于2的成员
        where name like '%田%' and state < 2*/

        LambdaQueryWrapper<Member> lambdaQuery = new QueryWrapper<Member>().lambda();
        lambdaQuery.like(Member::getName, "田").lt(Member::getState, 2);

        List<Member> memberList = memberMapper.selectAllMembers(lambdaQuery);

        return ResultUtil.success(memberList);
    }

    @RequestMapping("/selectByPage")
    public Result selectByPage() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("state", 1);

        //  new Page<>(1, 2，false)中第三个参数为不查询总记录数，默认为true查询总记录数
        //  第一种：以对象的形式输出每页的记录
        IPage<Member> memberPage = new Page<>(1, 2);
        IPage<Member> selectPage = memberMapper.selectPage(memberPage, queryWrapper);

        //  第二种：以map集合的形式输出每页的记录
        /*IPage<Map<String,Object>> memberPage = new Page<>(1, 2);
        IPage<Map<String, Object>> selectPage = memberMapper.selectMapsPage(memberPage, queryWrapper);*/

        List<Object> list = new ArrayList<>();
        list.add(selectPage.getPages());        //  总页数
        list.add(selectPage.getTotal());        //  总记录数
        list.addAll(selectPage.getRecords());   //  当前页数据

        System.out.println(selectPage.getRecords());

        return ResultUtil.success(list);
    }

    @RequestMapping("/selectMyPage")
    public Result selectMyPage() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("state", 1);

        IPage<Member> memberPage = new Page<>(1, 2);
        IPage<Member> selectPage = memberMapper.selectMemberPage(memberPage, queryWrapper);

        List<Object> list = new ArrayList<>();
        list.add(selectPage.getPages());        //  总页数
        list.add(selectPage.getTotal());        //  总记录数
        list.addAll(selectPage.getRecords());   //  当前页数据

        System.out.println(selectPage.getRecords());

        return ResultUtil.success(list);
    }

}
