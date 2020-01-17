package com.ethan.codegen.service;

import com.ethan.codegen.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-01-17
 */
public interface AccountService extends IService<Account> {

    List<Account> getAll();
}
