package com.app.backend.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.app.backend.common.model.JsonResponse;
import com.app.backend.common.util.RSAUtil;
import com.app.backend.user.controller.support.UserSupport;
import com.app.backend.user.model.User;
import com.app.backend.user.model.UserFavorite;
import com.app.backend.user.service.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class UserFavoritesController {

  private final UserFavoritesService userFavoritesService;

  public UserFavoritesController(UserFavoritesService userFavoritesService) {
    this.userFavoritesService = userFavoritesService;
  }

  //get the favorites list
  @GetMapping
  public Map<String, List<String>> getUserFavorites(@RequestParam Long userId) {
    
    List<UserFavorite> favorites = userFavoritesService.getUserFavorites(userId);
    return favorites.stream().collect(Collectors.groupingBy(
        UserFavorite::getCategory,
        Collectors.mapping(UserFavorite::getAlias, Collectors.toList())
    ));
  }

  //add the favorites list
  @PostMapping
  public void addUserFavorite(@RequestBody UserFavorite userFavorite) {
      userFavoritesService.addUserFavorite(userFavorite);
  }

  //delete the favorties list
  @DeleteMapping
  public void removeUserFavorite(@RequestParam Long userId, 
                                @RequestParam String category, 
                                @RequestParam String alias) {
      userFavoritesService.removeUserFavorite(userId, category, alias);
  }



}
