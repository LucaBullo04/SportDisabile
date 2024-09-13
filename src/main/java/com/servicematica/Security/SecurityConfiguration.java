package com.servicematica.Security;

import com.servicematica.Model.UtentiAutenticazione.Ruoli;
import com.servicematica.Service.AutenticazioneService;
import jakarta.servlet.DispatcherType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AutenticazioneService service;

    //Authorization logic
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] permessiGenerali = {"/js/**", "/login", "/passwordDimenticata", "/images/**", "/adminLTE/**", "/impostaPassword"};
        String[] permessiAssociazione = {"/", "", "/associazione/*", "/attivita/*", "/assistenza", "/assistenza/inviaMessaggio", "/listaAttivita/associazione"};
        String[] permessiAssociazioneDisabilitata = {"/associazioneDisabilitata"};
        String[] permessiAdmin = {"/inserisciAssociazione", "/listaAssociazioni", "/gestioneAttivita/*", "/gestioneListeAttivita", "/listaAttivita/admin"};
        
        httpSecurity.csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(permessiGenerali).permitAll();
                    registry.requestMatchers(permessiAssociazione).hasRole(Ruoli.Associazione.name());
                    registry.requestMatchers(permessiAssociazioneDisabilitata).hasRole(Ruoli.AssociazioneDisabilitata.name());
                    registry.requestMatchers(permessiAdmin).hasRole(Ruoli.Admin.name());
                    
                    registry.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll(); //Questa riga di codice permette di utilizzare il customLogin e di evitare l'errore TOO_MANY_REDIRECT
                    registry.anyRequest().authenticated();
                })
                .formLogin(loginForm -> {
                    loginForm.loginPage("/login").successHandler(new AuthenticationSuccessHandler()).permitAll();
                });

                return  httpSecurity.build();
    }

    //Authentication logic
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    //password encoding logic
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
