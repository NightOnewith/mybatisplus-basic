package com.ethan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ASUS on 2019/12/19.
 */
@Data
@TableName("sys_member")
public class Member {

    /**
     * //1.设置主键自增，数据库同样要进行主键自增设置
     * @TableId(type = IdType.AUTO)
     *
     * //2.数据库不设置主键自增，实体类设置id则使用设置的id进行插入，实体类不设置id则使用默认的雪花算法
     * @TableId(type = IdType.NONE)
     *
     * //3.这个就是雪花算法（默认）
     * @TableId(type = IdType.ID_WORKER)
     *
     * //4.这个雪花算法要求id是String类型的
     * @TableId(type = IdType.ID_WORKER_STR)
     *
     * //5.UUID要求id为String类型
     * @TableId(type = IdType.UUID)
     *
     *  注：3、4、5 这三种主键策略只有当插入对象ID为空，才自动填充！
     */

    @TableId(type = IdType.AUTO)
    private Long id;

    //@TableField(condition = SqlCondition.LIKE)
    //@TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    private String password;

    private Integer state;

    private Long superId;

    private LocalDateTime createTime;

    //  邮箱
    /*@TableField(exist = false)
    private String Email;*/
}
