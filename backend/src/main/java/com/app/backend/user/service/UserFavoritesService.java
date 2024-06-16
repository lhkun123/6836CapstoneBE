package com.app.backend.user.service;


import com.app.backend.user.mapper.UserFavoritesMapper;
import com.app.backend.user.model.UserFavorite;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserFavoritesService {
  private final UserFavoritesMapper userFavoritesMapper;

  public UserFavoritesService(UserFavoritesMapper userFavoritesMapper) {
    this.userFavoritesMapper = userFavoritesMapper;
  }

  public List<UserFavorite> getUserFavorites(Long userId) {
    return userFavoritesMapper.getUserFavorites(userId);   
  }

  public void addUserFavorite(UserFavorite userFavorite) {
    userFavoritesMapper.insertUserFavorite(userFavorite);
  }

  public void removeUserFavorite(Long userId, String category, String alias) {
    userFavoritesMapper.deleteUserFavorite(userId, category, alias);
  }

}
