package com.app.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BackendApplication {

   public static void main(String[] args) {
      Dotenv dotenv = Dotenv.configure().load();
      System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
      System.setProperty("OPENAI_API_URL", dotenv.get("OPENAI_API_URL"));
      SpringApplication.run(BackendApplication.class, args);
   }

}
