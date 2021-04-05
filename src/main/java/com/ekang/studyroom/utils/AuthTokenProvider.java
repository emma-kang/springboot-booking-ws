package com.ekang.studyroom.utils;

import javax.servlet.http.HttpServletRequest;

public interface AuthTokenProvider {
    String parseTokenString(HttpServletRequest request);
    AuthenticationToken issue(Long userId);
    Long getTokenOwnerId(String token);
    boolean validToken(String token);
}
