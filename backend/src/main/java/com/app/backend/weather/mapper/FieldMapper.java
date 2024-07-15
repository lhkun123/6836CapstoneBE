package com.app.backend.weather.mapper;

import com.app.backend.weather.model.Field;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FieldMapper extends BaseMapper<Field> {

    @Update("UPDATE field SET image_url = #{previewImageUrl}, " +
            "preview_image_url1 = #{previewImageUrl1}, " +
            "preview_image_url2 = #{previewImageUrl2}, " +
            "preview_image_url3 = #{previewImageUrl3}, " +
            "preview_image_url4 = #{previewImageUrl4}, " +
            "preview_image_url5 = #{previewImageUrl5} " +
            "WHERE name = #{name}")
    int updatePreviewImagesByName(@Param("name") String name,
                                  @Param("previewImageUrl") String previewImageUrl,
                                  @Param("previewImageUrl1") String previewImageUrl1,
                                  @Param("previewImageUrl2") String previewImageUrl2,
                                  @Param("previewImageUrl3") String previewImageUrl3,
                                  @Param("previewImageUrl4") String previewImageUrl4,
                                  @Param("previewImageUrl5") String previewImageUrl5);

    @Update("UPDATE field SET image_url = #{previewImageUrl}, " +
            "preview_image_url1 = #{previewImageUrl1}, " +
            "preview_image_url2 = #{previewImageUrl2}, " +
            "preview_image_url3 = #{previewImageUrl3}, " +
            "preview_image_url4 = #{previewImageUrl4}, " +
            "preview_image_url5 = #{previewImageUrl5} " +
            "WHERE location = #{location}")
    int updatePreviewImagesByLocation(@Param("location") String location,
                                @Param("previewImageUrl") String previewImageUrl,
                                @Param("previewImageUrl1") String previewImageUrl1,
                                @Param("previewImageUrl2") String previewImageUrl2,
                                @Param("previewImageUrl3") String previewImageUrl3,
                                @Param("previewImageUrl4") String previewImageUrl4,
                                @Param("previewImageUrl5") String previewImageUrl5);    


    @Select("SELECT location FROM field WHERE name = #{name}")
    String selectLocationByName(@Param("name") String name);


    @Delete("DELETE FROM field WHERE name = #{name}")
    String deleteLocationByName(@Param("name") String name);
}