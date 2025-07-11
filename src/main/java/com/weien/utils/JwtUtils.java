package com.weien.utils;

import com.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000; // 24小时过期

    public static String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .subject(user.getUsername())    // 设置主题为用户名
                .issuedAt(now)                 // 设置签发时间
                .expiration(expiration)         // 设置过期时间
                .id(UUID.randomUUID().toString()) // 设置JWT的唯一标识符
                .signWith(KEY)                    // 使用固定的密钥签名
                .compact();
    }

    // 添加token验证方法
    public static Jws<Claims> verifyToken(String token) throws JwtException{
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }
}