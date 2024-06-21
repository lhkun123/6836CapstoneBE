package com.app.backend.user.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@TableName("user_favorites")
public class UserFavorite {

  @TableId     ///(value="userId")
  private Long id;

  @TableField
  private Long userId;

  @TableField
  private String category;

  @TableField
  private String alias;

  // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="America/Vancouver")
  // @TableField(value="createTime",fill = FieldFill.INSERT)
  // private Date createTime;

  // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="America/Vancouver")
  // @TableField(value="updateTime",fill = FieldFill.INSERT_UPDATE)
  // private Date updateTime;
  
}
