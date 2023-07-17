package com.lh.dome.common.utils;

import com.lh.dome.common.exception.ServiceException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 *  AESE加密
 * @  lihong
 * @date 2023/05/17
 */
public class AESExample {
    private static final String SECRET_KEY = "ThisIsASecretKey"; // 密钥，需要保密

    /**
     * 加密
     *
     * @param data 数据
     * @return {@code String}
     * @throws Exception 异常
     */
    public static String encrypt(String data) {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        String res = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            res = Base64.getEncoder().encodeToString(encryptedData);
        }catch (Exception e){
            throw  new ServiceException("文件加密失败");
        }
        return res;
    }

    /**
     * 解密
     *
     * @param encryptedData 加密数据
     * @return {@code String}
     * @throws Exception 异常
     */
    public static String decrypt(String encryptedData)  {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        String res = null;
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            res = new String(decryptedData, StandardCharsets.UTF_8);
        }catch (Exception e){
            throw  new ServiceException("解密失败");
        }
        return res;
    }

}
