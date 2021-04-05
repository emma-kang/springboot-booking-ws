package com.ekang.studyroom.utils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtAuthToken implements AuthenticationToken {
    private String token;
}
