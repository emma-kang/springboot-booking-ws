package com.ekang.studyroom.config.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtAuthToken implements AuthenticationToken {
    private String token;
}
