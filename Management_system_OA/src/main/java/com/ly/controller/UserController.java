package com.ly.controller;

import com.ly.commons.Code;
import com.ly.service.UserService;
import com.ly.vo.ResultVo;
import com.ly.vo.UserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO 1.账号信息查询(根据账户名称) getUser[user:query]
 *                   2.账号信息添加 addUser[user:update_delete_insert]
 *                   3.账号信息修改 editUser[user:update_delete_insert]
 *                   4.通过用户名查询账户权限信息 getAuthByUsername[user:query]
 * @Author 赖昱
 * @Date 2023/5/17 - 20:47
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('user:query')")
    public ResultVo getUser(@RequestBody String username){
        ResultVo resultVo = ResultVo.FAIL();
        // 确定参数不为空那么我们就开始查询
        if (username == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 查询信息
        UserVo userVo = userService.getUserByUsername(username);
        if (userVo == null){
            resultVo.setEnumCode(Code.NOT_GET_PARAM);
            return resultVo;
        }
        // 到这说明有数据交给前端
        resultVo.setEnumCode(Code.GET_SUCCESS);
        resultVo.setObject(userVo);
        return resultVo;
    }

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('user:update_delete_insert')")
    public ResultVo addUser(@RequestBody UserVo userVo){
        ResultVo resultVo = ResultVo.FAIL();
        // 确定参数不为空那么我们就开始添加
        if (userVo == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 添加参数
        int i = userService.insertSelective(userVo);
        if (i != 1){
            resultVo.setEnumCode(Code.ADD_FAIL);
            return resultVo;
        }
        resultVo.setEnumCode(Code.ADD_SUCCESS);
        return resultVo;
    }

    @PatchMapping("/edit")
    @PreAuthorize("hasAuthority('user:update_delete_insert')")
    public ResultVo editUser(@RequestBody UserVo userVo){
        ResultVo resultVo = ResultVo.FAIL();
        if (userVo == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 修改
        int i = userService.updateByPrimaryKeySelective(userVo);
        if (i != 1){
            resultVo.setEnumCode(Code.EDIT_FAIL);
            return resultVo;
        }
        resultVo.setEnumCode(Code.EDIT_SUCCESS);
        return resultVo;
    }

    @GetMapping("/getAuthByUsername")
    @PreAuthorize("hasAuthority('user:query')")
    public ResultVo getAuthByUsername(@RequestBody String username){
        ResultVo resultVo = ResultVo.FAIL();
        if (username == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        List<String> authList = userService.getAuthByUsername(username);
        if (authList == null){
            resultVo.setEnumCode(Code.USERNAME_NOT_FOUND);
            return resultVo;
        }
        // 擦好像到了权限
        resultVo.setEnumCode(Code.GET_SUCCESS);
        resultVo.setList(authList);
        return resultVo;
    }

}
