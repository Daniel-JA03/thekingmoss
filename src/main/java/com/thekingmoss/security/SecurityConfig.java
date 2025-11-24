package com.thekingmoss.security;

import com.thekingmoss.security.filter.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService userDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/imagesProducts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categoria/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/producto/**").permitAll()

                        // ADMINISTRADOR - Categoria
                        .requestMatchers(HttpMethod.POST, "/api/categoria").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categoria/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categoria/**").hasAuthority("ROLE_ADMIN")

                        // Producto
                        .requestMatchers(HttpMethod.POST, "/api/producto").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/producto/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/producto/**").hasAuthority("ROLE_ADMIN")

                        // Pedido (todos protegidos)
                        .requestMatchers(HttpMethod.GET, "/api/pedidos/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/api/pedidos").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/api/pedidos/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/pedidos/**").hasAuthority("ROLE_ADMIN")

                        // DocumentoIdentidad
                        .requestMatchers(HttpMethod.POST, "/api/documentoIdentidad").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/documentoIdentidad/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/api/documentoIdentidad/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/documentoIdentidad/**").hasAuthority("ROLE_ADMIN")

                        // Direccion
                        .requestMatchers(HttpMethod.GET, "/api/direccion").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/direccion/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/api/direccion").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/api/direccion/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/direccion/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                        // Producto Imagen
                        .requestMatchers(HttpMethod.GET, "/api/productoImagen").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/productoImagen/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/productoImagen").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/productoImagen/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/productoImagen/**").hasAuthority("ROLE_ADMIN")

                        // Contacto
                        .requestMatchers(HttpMethod.GET, "/api/contacto").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contacto").permitAll()

                        // Carrito
                        .requestMatchers(HttpMethod.GET, "/api/carrito/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/api/carrito/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")  // agrega
                        .requestMatchers(HttpMethod.PUT, "/api/carrito/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")   // actualiza
                        .requestMatchers(HttpMethod.DELETE, "/api/carrito/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER") // producto y vaciar

                        // Pago 
                        .anyRequest().authenticated()
                )
                .authenticationProvider(provider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    // Configurar el codificador de contraseña
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Proveedor de autenticacion con detalles de usuario y codificacion de constraseñas
    @Bean
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);  // Usa el servicio personalizado para cargar usuarios
        provider.setPasswordEncoder(passwordEncoder());  // Usa el codificador BCrypt
        return provider;
    }

    // Configura el AuthenticationManager, necesario para la autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Configuración de CORS para permitir solicitudes desde localhost:4200 (Frontend Angular)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
