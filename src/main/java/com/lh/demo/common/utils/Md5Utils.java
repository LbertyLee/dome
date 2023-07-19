package com.lh.demo.common.utils;

import com.lh.demo.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;

/**
 * Md5加密方法
 *
 */
public class Md5Utils
{
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    public static String fileMd5(Path filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath.toString());
            return fileMd5(fis);
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
    }

    public static String fileMd5(FileInputStream fis) {
        try {
            FileChannel fileChannel = fis.getChannel();

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                md5.update(buffer);
                buffer.clear();
            }

            fileChannel.close();
            fis.close();

            byte[] md5Bytes = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : md5Bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("MD5 Error...", e);
            throw new SystemException(e.getMessage());
        }
    }
    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }
}
