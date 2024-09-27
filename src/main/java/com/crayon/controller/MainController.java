package com.crayon.controller;

import com.crayon.facade.JasyptFacade;
import com.crayon.model.Result;
import com.crayon.utils.AESUtil;
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

    @Autowired
    AESUtil aesUtil;

    /**
     * 使用jasypt加密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/encrypt")
    public Result encrypt(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String str = data.get("str");
        if (StringUtils.isBlank(str)) {
            return Result.fail("明文不能为空");
        }
        String encrypt = jasyptFacade.encrypt(str);
        Map<String, String> map = Map.of("encrypt", encrypt);
        return Result.success("加密成功", map);
    }

    /**
     * 使用jasypt解密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/decrypt")
    public Result decrypt(@RequestBody Map<String, String> data, HttpServletRequest request) {
        if (data.isEmpty()) {
            return Result.fail("密文不能为空");
        }
        String decrypt = jasyptFacade.decrypt(data.get("str"));
        if (null == decrypt) {
            return Result.fail("密文被篡改");
        }
        Map<String, String> map = Map.of("decrypt", decrypt);
        return Result.success("解密成功", map);
    }

    /**
     * 使用固定的向量值加密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/encryptByAES")
    public Result encryptByAES(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String str = data.get("str");
        if (StringUtils.isBlank(str)) {
            return Result.fail("明文不能为空");
        }
        String encrypt = null;
        try {
            encrypt = aesUtil.encrypt(str);
        } catch (Exception e) {
            log.error("加密失败", e);
            return Result.fail("加密失败");
        }
        return Result.success("加密成功", encrypt);
    }

    /**
     * 使用固定的向量值解密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/decryptByAES")
    public Result decryptByAES(@RequestBody Map<String, String> data, HttpServletRequest request) {
        if (data.isEmpty()) {
            return Result.fail("密文不能为空");
        }
        String decrypt = null;
        try {
            decrypt = aesUtil.decrypt(data.get("str"));
        } catch (Exception e) {
            log.error("解密失败", e);
            return Result.fail("解密失败");
        }
        Map<String, String> map = Map.of("decrypt", decrypt);
        return Result.success("解密成功", map);
    }

    /**
     * 使用随机生成的向量值加密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/encryptByAES2")
    public Result encryptByAESWithRandomVectorKey(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String str = data.get("str");
        if (StringUtils.isBlank(str)) {
            return Result.fail("明文不能为空");
        }
        String encrypt = null;
        String vectorKey = AESUtil.generateVectorKey();
        try {
            encrypt = aesUtil.encrypt(str, vectorKey);
        } catch (Exception e) {
            log.error("加密失败", e);
            return Result.fail("加密失败");
        }
        Map<String, String> map = Map.of("encrypt", encrypt, "vectorKey", vectorKey);
        return Result.success("加密成功", map);
    }

    /**
     * 使用随机生成的向量值解密
     *
     * @param data
     * @param request
     * @return
     */
    @PostMapping("/decryptByAES2")
    public Result decryptByAESWithRandomVectorKey(@RequestBody Map<String, String> data, HttpServletRequest request) {
        if (data.isEmpty()) {
            return Result.fail("密文不能为空");
        }
        String decrypt = null;
        String encrypt = data.get("str");
        String vectorKey = data.get("vectorKey");
        try {
            decrypt = aesUtil.decrypt(encrypt, vectorKey);
        } catch (Exception e) {
            log.error("解密失败", e);
            return Result.fail("解密失败");
        }
        Map<String, String> map = Map.of("decrypt", decrypt);
        return Result.success("解密成功", map);
    }


}
