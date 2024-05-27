package com.app.backend.user.controller.support;

import com.app.backend.common.model.exception.ConditionException;
import com.app.backend.common.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
public class UserSupport {
   public Long getCurrentUserId(){
      ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

      String token=servletRequestAttributes.getRequest().getHeader("token");
      Long userId= TokenUtil.verifyToken(token);
      if(userId<0){
         throw  new ConditionException("illegal user!");
      }
      return  userId;
   }

}
