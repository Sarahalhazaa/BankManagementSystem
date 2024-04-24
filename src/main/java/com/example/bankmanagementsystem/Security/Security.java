package com.example.bankmanagementsystem.Security;

import com.example.bankmanagementsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean

    public SecurityFilterChain springSecurityFilterChainn(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                //Account
                .requestMatchers("/api/v1/account/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/Update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/ListUsersAccounts").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/ActiveBankAccount").hasAuthority( "EMPLOYEE")
                .requestMatchers("/api/v1/account/BlockBankAccount").hasAuthority( "EMPLOYEE")
                .requestMatchers("/api/v1/account/DepositMoney").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/WithdrawMoney").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/TransferFundsBetweenAccounts").hasAuthority("CUSTOMER")

                //Auth
                .requestMatchers("/api/v1/auth/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/Update").hasAnyAuthority("CUSTOMER" , "EMPLOYEE")
                .requestMatchers("/api/v1/auth/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/logOut").permitAll()
                //Customer
                .requestMatchers("/api/v1/customer/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/Update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete").hasAuthority("ADMIN")
               .requestMatchers("/api/v1/customer/register").permitAll()
                //Employee
                .requestMatchers("/api/v1/employee/Update").hasAuthority( "EMPLOYEE")
                .requestMatchers("/api/v1/employee/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/register").permitAll()
                //ProfileCustomer
                .requestMatchers("/api/v1/profileCustomer/Update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/profileCustomer/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/profileCustomer/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/profileCustomer/register").hasAuthority("CUSTOMER")
                //ProfileEmployee
                .requestMatchers("/api/v1/profileEmployee/Update").hasAuthority( "EMPLOYEE")
                .requestMatchers("/api/v1/profileEmployee/delete").hasAuthority( "EMPLOYEE")
                .requestMatchers("/api/v1/profileEmployee/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/profileEmployee/register").hasAuthority( "EMPLOYEE")

                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/vi/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
