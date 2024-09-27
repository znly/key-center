package com.crayon.facade.Impl;

import com.crayon.facade.JasyptFacade;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hengye
 * @version 1.0
 * @Description
 * @date 2024/9/26 17:41
 */
@Service
public class JasyptFacadeImpl implements JasyptFacade {
    @Autowired
    StringEncryptor stringEncryptor;

    @Override
    public String encrypt(String plainText) {
        return stringEncryptor.encrypt(plainText);
    }

    @Override
    public String decrypt(String encryptText) {
        String decryptText;
        try {
            decryptText = stringEncryptor.decrypt(encryptText);
        }catch (Exception e){
            return null;
        }
        return decryptText;
    }
}
