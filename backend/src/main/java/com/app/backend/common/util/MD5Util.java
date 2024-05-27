package com.app.backend.common.util;

import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.*;


public class MD5Util {
   public static String sign(String content,String salt,String charset){
      content=content+salt;
      return DigestUtils.md5DigestAsHex(getContentBytes(content,charset));
   }
   public  static boolean verify(String content,String sign,String salt,String charset){
      content+=salt;

      String mysign=DigestUtils.md5DigestAsHex(getContentBytes(content,charset));
      return mysign.equals(sign);

   }
   private static byte[] getContentBytes(String content, String charset) {
      if(!"".equals(charset)){
         try {
            return  content.getBytes(charset);
         }catch (UnsupportedEncodingException e){
            throw new RuntimeException();
         }
      }else{
         return  content.getBytes();
      }
   }
}
