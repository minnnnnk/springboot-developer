/*
package me.kimmingyu.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.kimmingyu.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

    //스프링 시큐리티 기능 비활성화하는 설정
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console()) // H2 DB 콘솔 접속 허용
                .requestMatchers(new AntPathRequestMatcher("/static/**")); // 정적 자원 허용
    }

    //특정 HTTP 요청에 대한 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests(auth -> auth //인증, 인가 설정
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll() // 인증없이 접근 가능하게 설정
                        .anyRequest().authenticated() // 위 URI외에 접근은 인증 필요
                )
                .formLogin(formLogin -> formLogin //폼 기반 로그인 설정
                        .loginPage("/login") // 로그인 페이지
                        .defaultSuccessUrl("/articles") // 로그인 성공 후 리다이렉트 되는 URI
                )
                .logout(logout -> logout //로그아웃 설정
                        .logoutSuccessUrl("/login") //로그아웃 성공시 리다이렉트 되는 URI
                        .invalidateHttpSession(true) //세션 무효화 설정
                )
                .csrf(AbstractHttpConfigurer :: disable) //csrf 비활성화
                .build();
    }

    //인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
        throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
*/
