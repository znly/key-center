package com.crayon.facade;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;


/**
 * @Author: znly
 * @Description:
 * @Date: 2024/4/2 15:04
 */
public interface AESFacade {


    /**
     * AES/CBC/PKCS5Padding 加密
     *
     * @param content :待加密的内容.
     * @return 返回 Cipher 加密后的数据，对加密后的字节数组用 Base64 进行编码转成了可视字符串，如 7giH2bqIMH3kDMIg8gq0nY
     * @throws Exception
     */
    public String encrypt(String content) throws Exception;

    /**
     * AES/CBC/PKCS5Padding 解密
     *
     * @param base64Encode :待解密的内容，因为加密时使用了 Base64 进行了编码，所以这里传入的也是 Base64 编码后的可视化字符串
     * @return
     * @throws Exception
     */
    public String decrypt(String base64Encode) throws Exception;

    /**
     * AES/CBC/PKCS5Padding 加密
     *
     * @param content   :待加密的内容
     * @param vectorKey :偏移向量值
     * @return 返回 Cipher 加密后的数据，对加密后的字节数组用 Base64 进行编码转成了可视字符串，如 7giH2bqIMH3kDMIg8gq0nY
     * @throws Exception
     */
    public String encrypt(String content, String vectorKey) throws Exception;

    /**
     * AES/CBC/PKCS5Padding 解密
     *
     * @param base64Encode :待解密的内容，因为加密时使用了 Base64 进行了编码，所以这里传入的也是 Base64 编码后的可视化字符串
     * @param vectorKey    :偏移向量值
     * @return
     * @throws Exception
     */
    public String decrypt(String base64Encode, String vectorKey) throws Exception;

    /**
     * 随机生成向量值
     *
     * @return
     */
    public static String generateVectorKey() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

}
