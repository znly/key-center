package com.crayon.controller;

import com.crayon.facade.JasyptFacade;
import com.crayon.model.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author hengye
 * @version 1.0
 * @Description 主要的控制器
 * @date 2024/9/26 17:25
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class MainController {

    @Autowired
    JasyptFacade jasyptFacade;

    @PostMapping("/encrypt")
    public Result encrypt(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String str = data.get("str");
        if (StringUtils.isBlank(str)) {
            return Result.fail("明文不能为空");
        }
        String encrypt = jasyptFacade.encrypt(str);
        return Result.success("加密成功", encrypt);
    }

    @PostMapping("/decrypt")
    public Result decrypt(@RequestBody Map<String, String> data, HttpServletRequest request) {
        if (data.isEmpty()) {
            return Result.fail("密文不能为空");
        }
        String decrypt = jasyptFacade.decrypt(data.get("str"));
        if (null == decrypt) {
            return Result.fail("密文被篡改");
        }
        return Result.success("解密成功", decrypt);
    }

}
