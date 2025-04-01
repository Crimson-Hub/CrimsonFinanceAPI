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
                        .requestMatchers(HttpMethod.POST, "/api/profile/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/profile/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/profile/changePassword").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/profile/update").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/profile/profiles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/profile/profileSummary").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/profile/profile").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/profile/delete").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/account/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/account/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/account/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/account/accounts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/account/accountsBalance").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/account/transactions").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/card/assign").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/card/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/card/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/card/cards").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/card/transactions").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/card/invoice/assign").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/card/invoices").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/transaction").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/transaction/cardExpense").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/transaction/revenuesBalance").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/transaction/revenuesBalance/top5").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/transaction/expensesBalance").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/transaction/expensesBalance/top5").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/transaction/cardExpenses").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/transaction/delete").permitAll()
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
