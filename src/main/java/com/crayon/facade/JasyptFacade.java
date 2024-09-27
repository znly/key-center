package com.crayon.facade;

/**
 * @author hengye
 * @version 1.0
 * @Description 加密工具类facade
 * @date 2024/9/26 17:40
 */
public interface JasyptFacade {
    String encrypt(String plainText);

    String decrypt(String encryptText);
}
