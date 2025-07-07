// Path: src/main/java/ro/fcdynamis/club/config/SecurityConfig.java
package ro.fcdynamis.club.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource ds) {
        JdbcUserDetailsManager uds = new JdbcUserDetailsManager(ds);
        uds.setUsersByUsernameQuery(
          "SELECT username, parola, true FROM useri WHERE username = ?");
        uds.setAuthoritiesByUsernameQuery(
          "SELECT username, 'ROLE_USER' FROM useri WHERE username = ?");
        return uds;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors(Customizer.withDefaults())
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              .requestMatchers(OPTIONS, "/api/**").permitAll()
              .requestMatchers(GET,     "/api/**").permitAll()
              .requestMatchers("/api/**").authenticated()
              .requestMatchers("/css/**", "/js/**", "/error").permitAll()
              .anyRequest().authenticated()
          )
          // folosește login form-ul implicit Spring Security
          .formLogin(Customizer.withDefaults())
          .logout(logout -> logout
              .logoutUrl("/logout")
              .logoutSuccessUrl("/login?logout")
              .permitAll()
          );
        return http.build();
    }
}
