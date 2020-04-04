package org.example.jwt

import com.google.gson.Gson
import io.jsonwebtoken.io.IOException
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.BufferedReader
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTLoginFilter internal constructor(url: String, authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(
//    AntPathRequestMatcher(s)
) {
    init {
        setAuthenticationManager(authenticationManager)
        setFilterProcessesUrl(url)
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
//        val username: String? = req.getParameter("username")
//        val password: String? = req.getParameter("password")
        val user:AccountCredentials = Gson().fromJson(JWTUtils.getBody(req), AccountCredentials::class.java)
        val authenticationToken = UsernamePasswordAuthenticationToken(user.username, user.password, emptyList<GrantedAuthority>())
        return authenticationManager.authenticate(authenticationToken)
}

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse, chain: FilterChain?, auth: Authentication) {
        JWTUtils.addAuthentication(res, auth.principal as org.springframework.security.core.userdetails.User)
    }



}