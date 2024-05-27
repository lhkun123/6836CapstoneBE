package com.app.backend.user.service;

import com.app.backend.common.model.exception.ConditionException;
import com.app.backend.common.util.MD5Util;
import com.app.backend.common.util.RSAUtil;
import com.app.backend.common.util.TokenUtil;
import com.app.backend.user.mapper.UserInfoMapper;
import com.app.backend.user.mapper.UserMapper;
import com.app.backend.user.model.User;
import com.app.backend.user.model.UserInfo;
import com.app.backend.user.model.constant.UserConstant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class UserService extends ServiceImpl<UserMapper, User>{

   @Autowired
   private UserMapper userMapper;

   @Autowired
   private UserInfoMapper userInfoMapper;

   @Transactional
   public void addUser(User user) {
      String email=user.getEmail();
      if(StringUtils.isNullOrEmpty(email)){
         throw new ConditionException("email cannot be empty!");
      }
      User dbUser=this.getUserByEmail(email);
      if(dbUser!=null){
         throw new ConditionException("This email has been registered");
      }
      Date now=new Date();
      String salt=String.valueOf(now);
      String password=user.getPassword();
      String encryptPassword;
      String md5Password= MD5Util.sign(password,salt,"UTF-8");
      try{
         encryptPassword= RSAUtil.encrypt(md5Password);
      }
      catch (Exception e){
         throw new ConditionException("fail to encode password");
      }
      user.setSalt(salt);
      user.setPassword(encryptPassword);
      user.setCreateTime(now);
      user.setUpdateTime(now);
      userMapper.insert(user);
      UserInfo userInfo=new UserInfo();
      userInfo.setUserId(user.getId());
      userInfo.setNick(UserConstant.NICK);
      userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
      userInfo.setGender(UserConstant.GENDER_MALE);
      userInfo.setCreateTime(now);
      userInfo.setUpdateTime(now);
      userInfoMapper.insert(userInfo);
   }
   public User getUserByEmail(String email){
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("email",email);
      return userMapper.selectOne(queryWrapper);
   }

   public String login(User user) throws Exception{
      String email=user.getEmail();
      if(StringUtils.isNullOrEmpty(email)){
         throw new ConditionException("Email cannot be empty!");
      }
      User dbUser=this.getUserByEmail(email);
      if(dbUser==null){
         throw new ConditionException("This email has not been registered");
      }
      String password=user.getPassword();
      String decryptPassword;
      try{
         decryptPassword=RSAUtil.decrypt(dbUser.getPassword());
      }
      catch (Exception e){
         throw new ConditionException("fail to encrypt password");
      }
      String salt= dbUser.getSalt();
      String md5Password=MD5Util.sign(password,salt,"UTF-8");
      if(!md5Password.equals(decryptPassword)){
         throw new ConditionException("Wrong Password");
      }
      return TokenUtil.generateToken(dbUser.getId());
   }

   public UserInfo getUserInfo(Long userId) {
      QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("userId",userId);
      return userInfoMapper.selectOne(queryWrapper);
   }

   public void updateUserInfos(UserInfo userInfo) {
      QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("userId",userInfo.getUserId());
      userInfo.setUpdateTime(new Date());
      userInfoMapper.update(userInfo,queryWrapper);
   }
   public void updateUser(User user) {
      user.setUpdateTime(new Date());
      userMapper.updateById(user);
   }

   public User getUserById(Long id) {
      return userMapper.selectById(id);
   }
}
