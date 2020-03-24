package org.example

import org.example.jwt.JWTAuthenticationFilter
import org.example.jwt.JWTLoginFilter
import org.example.services.SecurityUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.session.SessionManagementFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        //http.cors().disable()
        http
            .csrf()
            .disable()
            .authorizeRequests()
//            .antMatcher("/**").authorizeRequests()
            .antMatchers("/auth", "/users/create", "/api/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(CorsFilter(), SessionManagementFilter::class.java)
            .addFilterBefore(
                JWTLoginFilter("/auth", authenticationManager()),
                UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
    //}
    @Autowired
    private val customDetailsService: SecurityUserDetailsService? = null
    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}