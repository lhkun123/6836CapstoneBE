package com.app.backend.common.model.exception;
import com.app.backend.common.model.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConditionalExceptionHandler {

   @ExceptionHandler(value = Exception.class)
   @ResponseBody
   public JsonResponse<String> commonExceptionHandler(HttpServletRequest request, Exception e){
      String errorMsg = e.getMessage();
      if(e instanceof ConditionException){
         String errorCode = ((ConditionException)e).getCode();
         return new JsonResponse<>(errorCode, errorMsg);
      }else{
         return new JsonResponse<>("500",errorMsg);
      }
   }
}