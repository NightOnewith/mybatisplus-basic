package com.ethan.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.entity.MemberAR;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ASUS on 2020/1/13.
 */
@RestController
public class ARController {

    @RequestMapping("/ar_insert")
    public Result insert() {
        MemberAR memberAR = new MemberAR();
        memberAR.setId(10L);
        memberAR.setName("第时");
        memberAR.setPassword("12345");
        memberAR.setState(2);
        memberAR.setSuperId(1L);
        memberAR.setCreateTime(LocalDateTime.now());

        boolean insert = memberAR.insert();

        System.out.println(insert);
        return ResultUtil.success(insert);
    }

    @RequestMapping("/ar_select")
    public Result selectAR() {
        MemberAR memberAR = new MemberAR();
        //1.根据id查询
        //MemberAR ar = memberAR.selectById(10);
        // 或者这样
        //memberAR.setId(10L);
        //MemberAR ar = memberAR.selectById();

        //2.查询所有
        //List<MemberAR> ars = memberAR.selectAll();

        //3.根据条件查询
        //List<MemberAR> ars = memberAR.selectList(new QueryWrapper<MemberAR>().like("name", "第"));

        //4.查询符合条件的总数
        int count = memberAR.selectCount(new QueryWrapper<MemberAR>().eq("state", 1));

        return ResultUtil.success(count);
    }

    @RequestMapping("/ar_update")
    public Result updateAR() {
        MemberAR memberAR = new MemberAR();
        memberAR.setId(10L);
        memberAR.setCreateTime(LocalDateTime.now());

        boolean updateById = memberAR.updateById();

        return ResultUtil.success(updateById);
    }

    @RequestMapping("/ar_delete")
    public Result deleteAR() {
        MemberAR memberAR = new MemberAR();
        //删除数据库中不存在的数据也是返回true
        //1.根据id删除
        //boolean deleteById = memberAR.deleteById(10L);
        //或者这样
        //memberAR.setId(10L);
        //boolean deleteById = memberAR.deleteById();

        //2.根据条件删除
        boolean delete = memberAR.delete(new QueryWrapper<MemberAR>().like("name", "第"));

        return ResultUtil.success(delete);
    }

    @RequestMapping("/ar_insertOrUpdate")
    public Result insertOrUpdateAR() {
        MemberAR memberAR = new MemberAR();
        //1.插入操作
        //memberAR.setName("张强");
        //memberAR.setPassword("12345");
        //boolean insertOrUpdate = memberAR.insertOrUpdate();

        //2.更新操作
        memberAR.setId(10L);
        memberAR.setCreateTime(LocalDateTime.now());
        boolean insertOrUpdate = memberAR.insertOrUpdate();

        return ResultUtil.success(insertOrUpdate);
    }

    @RequestMapping("/ar_page")
    public Result pageAR() {
        MemberAR memberAR = new MemberAR();
        Page<MemberAR> selectPage = memberAR.selectPage(new Page<>(1, 4),
                new QueryWrapper<MemberAR>().eq("state", 1));

        List<MemberAR> selectPageRecords = selectPage.getRecords();

        return ResultUtil.success(selectPageRecords);
    }

}
