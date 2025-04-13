package com.crimsonhub.CrimsonFinanceAPI.configuration;

import com.crimsonhub.CrimsonFinanceAPI.infrastructure.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração de segurança do Spring Security para a aplicação.
 * <p>
 * Esta classe define a configuração de segurança, incluindo as políticas de autenticação,
 * autorização e filtros de segurança. Utiliza autenticação baseada em token (JWT) com
 * sessões sem estado.
 * </p>
 *
 * @author Crimson Finance
 * @version 1.0
 * @since 2025-01-01
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Filtro de segurança personalizado para validar tokens JWT.
     */
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Configura a cadeia de filtros de segurança do Spring Security.
     *
     * @param httpSecurity O objeto {@link HttpSecurity} usado para configurar a segurança.
     * @return Um bean {@link SecurityFilterChain} configurado.
     * @throws Exception Em caso de erro na configuração de segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/profiles").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/profiles/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/profiles/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/profiles/updatePassword").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/profiles/update").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/profiles/profile/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/profiles/profile/summary/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/profiles/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/accounts").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/accounts/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/accounts/balance").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/accounts/create/{profileId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/accounts/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/cards/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards/balance").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/cards/assign/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards/{profileId}/top").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/cards/{id}/invoiceAssign").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards/{id}/invoices").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/cards/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/accounts/transactions/{profileId}/{transactionTypeId}/total").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/accounts/transactions/{profileId}/{transactionTypeId}/top").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/accounts/transactions/{accountId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/accounts/transactions/{transactionId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/accounts/transactions/{transactionId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/cards/transactions/{cardId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards/transactions/{cardId}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/cards/transactions/{profileId}/total").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/cards/transactions/{transactionId}").hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configura o gerenciador de autenticação usado pelo Spring Security.
     *
     * @param authenticationConfiguration A configuração de autenticação do Spring Security.
     * @return Um bean {@link AuthenticationManager} configurado.
     * @throws Exception Em caso de erro ao recuperar o gerenciador de autenticação.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
