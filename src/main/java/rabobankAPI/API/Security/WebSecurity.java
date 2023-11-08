package rabobankAPI.API.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

import static rabobankAPI.API.Security.SecurityConstants.SIGN_IN_URL;
import static rabobankAPI.API.Security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/login/oauth2/code/google",
            "/oauth2/login/callback",
            "/login/oauth2/code/**",
            "/oauth2/**"
    };

    /**
     * source: https://www.youtube.com/watch?v=a-4LK0FiqNQ&ab_channel=KindsonTheTechPro
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http
                .csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().permitAll()
                .successHandler(oAuth2LoginSuccessHandler).and().logout(t -> t.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").permitAll())
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    //CORS fix: https://www.codegrepper.com/code-examples/java/HttpSecurity+cors+spring+boot
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("GET", "PUT", "POST", "DELETE");
//            }
//        };
//    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                        "http://localhost:8082/",
                        "http://localhost:3000/",
                        "https://accounts.google.com/",
                        "https://rabobank-portal.herokuapp.com/"
                )
        );
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(
                Arrays.asList(
                        "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers",
                        "Origin", "Cache-Control",
                        "Content-Type",
                        "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
