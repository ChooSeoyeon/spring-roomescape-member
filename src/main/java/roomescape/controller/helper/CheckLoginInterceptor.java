package roomescape.controller.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import roomescape.service.helper.CookieExtractor;
import roomescape.service.helper.JwtTokenProvider;

public class CheckLoginInterceptor implements HandlerInterceptor {
    private final CookieExtractor cookieExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    public CheckLoginInterceptor(CookieExtractor cookieExtractor, JwtTokenProvider jwtTokenProvider) {
        this.cookieExtractor = cookieExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = cookieExtractor.getToken(request.getCookies());
        jwtTokenProvider.validateToken(token);
        return true;
    }
}
