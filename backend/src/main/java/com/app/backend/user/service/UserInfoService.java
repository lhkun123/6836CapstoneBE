package com.app.backend.user.service;

import com.app.backend.user.mapper.UserInfoMapper;
import com.app.backend.user.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService  extends ServiceImpl<UserInfoMapper, UserInfo> {


}
