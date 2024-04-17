package api.materia.TPAPIFINAL.app.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.apache.tomcat.util.security.ConcurrentMessageDigest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;

import java.util.Base64;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	http.authorizeHttpRequests(
                (authz) -> authz.anyRequest().authenticated())
        .addFilterBefore(jwtAuth(), UsernamePasswordAuthenticationFilter.class)
        .csrf(AbstractHttpConfigurer::disable)
    	.cors(Customizer.withDefaults());

        return http.build();
    }
    
    @Bean
    public CorsFilter corsFilter()
    {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration config = new CorsConfiguration();
    	config.addAllowedOrigin("http://localhost:3000");
    	config.addAllowedHeader("Authorization");
    	config.addAllowedHeader("Content-Type");
    	config.addAllowedMethod("GET");
    	config.addAllowedMethod("POST");
    	config.addAllowedMethod("DELETE");
    	config.addAllowedMethod("PUT");
    	config.addAllowedMethod("OPTIONS");
    	source.registerCorsConfiguration("/**", config);
    	return new CorsFilter(source);
    }

    @Bean
    public SecretKey secretKey(){
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return secretKey;
    }

    @Bean
    public JWTAuthFilter jwtAuth(){
        return new JWTAuthFilter(secretKey());
    }

    //Como dejar algun endpoint fuera de la seguridad o autenticacion
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().requestMatchers("auth/login", "auth/register"));
    }
}
