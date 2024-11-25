package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
// authentication
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("trung").password(encoder.encode("123")).roles("ADMIN").build();
		UserDetails user = User.withUsername("user").password(encoder.encode("123")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth.requestMatchers("/", "/hello").permitAll().requestMatchers("/customer/**").authenticated()
		// .anyRequest().authenticated()

		).formLogin(Customizer.withDefaults()).build();
		
//		return http
//                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không sử dụng
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").permitAll() // Trang gốc được phép truy cập
//                        .requestMatchers("/customer/all").hasRole("ADMIN") // Chỉ ROLE_ADMIN truy cập
//                        .requestMatchers("/customer/**").hasRole("USER") // ROLE_USER cho các đường dẫn khác
//                )
//                .formLogin(Customizer.withDefaults()) // Form login mặc định
//                .build();

	}
}
