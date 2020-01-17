package com.ethan.codegen.controller;


import com.ethan.codegen.entity.Account;
import com.ethan.codegen.service.AccountService;
import com.ethan.entity.Result;
import com.ethan.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/getAll")
    public Result getAll() {
        List<Account> accounts = accountService.getAll();

        return ResultUtil.success(accounts);
    }

}

