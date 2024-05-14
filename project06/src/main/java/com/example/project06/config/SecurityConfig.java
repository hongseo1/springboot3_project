package com.example.project06.config;

import com.example.project06.config.CustomAuthenticationEntryPoint;
import com.example.project06.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form
                        .loginPage("/login") //로그인 페이지 URL 설정
                        .defaultSuccessUrl("/index")  //로그인 성공 시 이동할 URL 설정
                        .usernameParameter("email")  //로그인 시 사용할 파라미터 이름으로 email을 지정
                        .failureUrl("/login/error")  //로그인 실패 시 이동할 URL을 설정
                )
                .logout(logout -> logout
                        //로그아웃 URL을 설정
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/index")  //로그아웃 성공 시 이동할 URL을 설정
                )
                //HttpServletRequest를 사용해서 적용
                .authorizeHttpRequests(auth -> auth
                        //permitAll() 모든 사용자 접근 가능
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/**", "/images/**").permitAll()
                        //hasRole("ADMIN") 어드민 권한을 가져야지만 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //제외한 나머지 경로들은 모두 인증하도록 설정
                        .anyRequest().authenticated()
                )
                // 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {  //비밀번호 암호화를 위해 passwordEncoder를 지정

        return new BCryptPasswordEncoder();
    }
}