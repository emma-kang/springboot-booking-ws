package com.ekang.studyroom.config.security;

import javax.servlet.http.HttpServletRequest;

public interface AuthTokenProvider {
    String parseTokenString(HttpServletRequest request);
    AuthenticationToken issue(int userId);
    Long getTokenOwnerId(String token);
    boolean validToken(String token);
}
