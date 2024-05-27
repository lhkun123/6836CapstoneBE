package com.app.backend.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.app.backend.common.model.exception.ConditionException;
import java.util.Calendar;
import java.util.Date;
public class TokenUtil {
   private  static final String ISSUER="issuer";

   public static void main(String[] args) throws Exception{
      Long id=TokenUtil.verifyToken("eyJraWQiOiIxIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJpc3N1ZXIiLCJleHAiOjE3MTY3ODU0ODZ9.WSot6ZBsiecZJNctDQthh4twWSSUab58d5z621kEq2x0_tV9kGXvo-RYWD1yyXg7_k5WQIhODJySXE7EMsGejBRYeD0KfcC1kzaZaMWWo7CqSfvejiFkRqMTvb5wXIB6DUmAIv0WZtZY2Y6W2BbDa7bnmhJjjfHzjQwHpxiJO-s");
      System.out.println(id);
   }
   public  static String generateToken(Long userId) throws  Exception{

      Algorithm algorithm=Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
      Calendar calendar=Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(Calendar.MINUTE,10);
      return JWT.create().withKeyId(String.valueOf(userId)).withIssuer(TokenUtil.ISSUER).withExpiresAt(calendar.getTime()).sign(algorithm);

   }
   public static Long verifyToken(String token) {
      try{
         Algorithm algorithm=Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
         JWTVerifier jwtVerifier=JWT.require(algorithm).build();
         DecodedJWT jwt= jwtVerifier.verify(token);
         String userId=jwt.getKeyId();
         return  Long.valueOf(userId);
      }catch (TokenExpiredException e){
         throw new ConditionException("555","token expired");
      }catch (Exception e){
         throw  new ConditionException("500","illegal user token");
      }

   }
}
