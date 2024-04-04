package com.BlogApp.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "5";
    public static final String SORT_BY = "postId";
   public static final String SECRET_KEY = "66ae0b9845d0d7794f1db2998c3dd8f82fcd3a35218af86a94019c65a1e63ad5";
   public static final String[] ALLOWED_URLS ={"/api/v1/auth/**","/v3/api-docs","/swagger-ui/**"};
   public static  final String APP_NAME="BLOG APP";
   public static final String APP_STATUS = "working";
}
