package com.weien.config;

import com.weien.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.sendError(401, "未提供Token");
            return false;
        }

        try {
            JwtUtils.verifyToken(token);
            return true; // 验证通过
        } catch (ExpiredJwtException e) {
            response.sendError(401, "Token已过期");
            return false;
        } catch (Exception e) {
            response.sendError(401, "Token无效");
            return false;
        }
    }
}