package com.app.backend.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.backend.common.model.JsonResponse;
import com.app.backend.common.util.RSAUtil;
import com.app.backend.user.controller.support.UserSupport;
import com.app.backend.user.model.User;
import com.app.backend.user.model.UserInfo;
import com.app.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {
   @Autowired
   private UserService userService;

   @Autowired
   private UserSupport userSupport;

   @GetMapping("/users")
   public JsonResponse<UserInfo> getUserInfo(){
      Long userId=userSupport.getCurrentUserId();
      UserInfo userInfo=userService.getUserInfo(userId);
      return  new JsonResponse<>(userInfo);
   }

   @GetMapping("rsa-pks")
   public JsonResponse<String> getRsaPublicKey(){
      String pk= RSAUtil.getPublicKeyStr();
      return new JsonResponse<>(pk);
   }
   @PostMapping("/users")
   public JsonResponse<String> addUser(@RequestBody User user){
      userService.addUser(user);
      return JsonResponse.success();
   }
   @PostMapping("/user-tokens")
   public JsonResponse<String> login(@RequestBody User user) throws  Exception{
      String token=userService.login(user);
      return  new JsonResponse<>(token);
   }
   @PutMapping("/user-infos")
   public JsonResponse<String>  updateUserInfo(@RequestBody UserInfo userInfo){
      Long userId=userSupport.getCurrentUserId();
      userInfo.setUserId(userId);
      userService.updateUserInfos(userInfo);
      return  JsonResponse.success();
   }

   @PutMapping("/users")
   public JsonResponse<String>  updateUsers(@RequestBody User user){
      Long userId=userSupport.getCurrentUserId();
      user.setId(userId);
      userService.updateUser(user);
      return  JsonResponse.success();
   }
}
