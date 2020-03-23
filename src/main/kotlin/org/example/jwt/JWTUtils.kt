package org.example.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.AlgorithmConstraints
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object JWTUtils {


    private const val expiration: Long = 100L
    private const val secret = "HellomydearfriendsHellomydearfriendsHellomydearfriends"
    private const val header = "Authorization"



    var secretKey: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray()) //Keys.secretKeyFor(SignatureAlgorithm.HS256)//
   // var secretKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    fun UserDetails.createJwt(): String {

        val claims = HashMap<String, Any>()
//        claims.put("roles", this.roles)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(this.username)
            .setExpiration(Date(TimeUnit.HOURS.toMillis(Date().time) + TimeUnit.HOURS.toMillis(expiration)))
            .signWith(secretKey, SignatureAlgorithm.HS256).compact()//.signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun addAuthentication(response: HttpServletResponse, user: UserDetails) {
        val jwt = user.createJwt()
        response.setHeader("Access-Control-Allow-Origin", "http://134.122.77.59:8080") //"http://localhost:8080")//
        response.writer.write(jwt)
        response.writer.flush()
        response.writer.close()
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(header) ?: return null


        val tokenBody = Jwts.parserBuilder()
            .setSigningKey(secretKey)//.getBytes(Charset.forName("UTF-8")
            .build()
            .parseClaimsJws(token)
            .body

        val username: String = tokenBody.subject
//        @Suppress("UNCHECKED_CAST")
//        val roles = tokenBody["roles"] as List<String>

//        val res = roles.mapTo(LinkedList<GrantedAuthority>()) { SimpleGrantedAuthority(it) }

//        logger.info(username + " logged in with authorities " + res)
        return UsernamePasswordAuthenticationToken(username, null, null)
    }
}