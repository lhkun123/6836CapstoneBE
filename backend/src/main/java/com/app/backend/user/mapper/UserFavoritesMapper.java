package com.app.backend.user.mapper;


import com.app.backend.user.model.UserFavorite;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UserFavoritesMapper {

  @Select("SELECT * FROM user_favorites WHERE user_id = #{userId}")
  List<UserFavorite> getUserFavorites(Long userId);

  @Insert("INSERT INTO user_favorites (user_id, category, alias) VALUES (#{userId}, #{category}, #{alias})")
  void insertUserFavorite(UserFavorite userFavorite);

  @Delete("DELETE FROM user_favorites WHERE user_id = #{userId} AND category = #{category} AND alias = #{alias}")
  void deleteUserFavorite(@Param("userId") Long userId, @Param("category") String category, @Param("alias") String alias);

}
