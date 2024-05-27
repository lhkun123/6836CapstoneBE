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
@TableName("user")
public class User {
   @TableId
   private  Long id;

   @TableField
   private String email;

   @TableField
   private  String password;

   @TableField
   private  String salt;

   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="America/Vancouver")
   @TableField(value="createTime",fill = FieldFill.INSERT)
   private Date createTime;

   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="America/Vancouver")
   @TableField(value="updateTime",fill = FieldFill.INSERT_UPDATE)
   private Date updateTime;

}
