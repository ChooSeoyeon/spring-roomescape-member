package roomescape.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import roomescape.controller.helper.CheckAdminInterceptor;
import roomescape.controller.helper.LoginMemberArgumentResolver;
import roomescape.service.MemberService;
import roomescape.service.helper.CookieExtractor;
import roomescape.service.helper.JwtTokenProvider;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final CookieExtractor cookieExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    public WebMvcConfig(CookieExtractor cookieExtractor,
                        JwtTokenProvider jwtTokenProvider,
                        MemberService memberService) {
        this.cookieExtractor = cookieExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CheckLoginInterceptor(cookieExtractor, jwtTokenProvider))
//                .excludePathPatterns("/login");
        registry.addInterceptor(new CheckAdminInterceptor(cookieExtractor, jwtTokenProvider))
                .addPathPatterns("/admin/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(cookieExtractor, jwtTokenProvider, memberService));
    }
}
