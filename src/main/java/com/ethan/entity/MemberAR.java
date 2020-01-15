package com.ethan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ASUS on 2019/12/19.
 */
@Data
@TableName("sys_member")
public class MemberAR extends Model<MemberAR> {

    @TableId
    private Long id;

    private String name;

    private String password;

    private Integer state;

    private Long superId;

    private LocalDateTime createTime;
}
