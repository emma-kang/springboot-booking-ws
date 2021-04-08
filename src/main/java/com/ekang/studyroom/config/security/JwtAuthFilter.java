package com.ekang.studyroom.config.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private AuthTokenProvider authTokenProvider;

    public JwtAuthFilter(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String token = authTokenProvider.parseTokenString(request);

        if (authTokenProvider.validToken(token)) {
            Long userNo = authTokenProvider.getTokenOwnerId(token);
//
//            try {
//                User member = (User) userService.loadUserByUsername(userNo.toString());
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member,
//                        member.getPassword(), member.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (UsernameNotFoundException e) {
//                throw new ForbiddenException("유효하지않은 인증토큰 입니다. 인증토큰 회원 정보 오류");
//            }
        }
        filterChain.doFilter(request, response);
        }
}
