package com.kms.giaphoang.apigateway.config;

import com.kms.giaphoang.apigateway.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

/**
 * @author : giaphoang
 * @mailto : hoanghuugiap241@gmail.com
 * @created : 8/31/2022, Wednesday
 * @project: spring-boot-stationery-chain
 **/
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private static final String PRODUCT_URL = "/api/v1/product/**";
    private static final String IMAGE_URL = "/api/v1/image/**";
    private static final String CATEGORY_URL = "/api/v1/category/**";
    private static final String INVENTORY_URL = "/api/v1/inventory/**";
    private static final String ORDER_URL = "/api/v1/order/**";
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable();
        // product service security
        http.authorizeExchange()
                .pathMatchers(HttpMethod.POST, PRODUCT_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.PUT, PRODUCT_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.DELETE, PRODUCT_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.GET, PRODUCT_URL).hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

        // image service security
        http.authorizeExchange()
                .pathMatchers(HttpMethod.POST, IMAGE_URL).hasRole(Role.ADMIN.name());

        // category service security
        http.authorizeExchange()
                .pathMatchers(HttpMethod.POST, CATEGORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.PUT, CATEGORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.DELETE, CATEGORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.GET, CATEGORY_URL).hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

        // inventory service security
        http.authorizeExchange()
                .pathMatchers(HttpMethod.POST, INVENTORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.PUT, INVENTORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.DELETE, INVENTORY_URL).hasRole(Role.ADMIN.name())
                .pathMatchers(HttpMethod.GET, INVENTORY_URL).hasAnyRole(Role.ADMIN.name(), Role.CUSTOMER.name());

        // discovery server security
        http.authorizeExchange()
                .pathMatchers("/eureka/**").permitAll()
                .anyExchange().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(grantedAuthoritiesExtractor());
        return http.build();
    }
    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization"));
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedOriginPattern(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
