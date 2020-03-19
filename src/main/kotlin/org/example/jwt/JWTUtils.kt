package org.example.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import java.security.Key
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal object JWTUtils {
    private const val expiration: Long = 100L
    private const val secret = "Hellomydearfriends"
    private const val header = "Authorization"
    var sercetKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun UserDetails.createJwt(): String {
        val claims = HashMap<String, Any>()
//        claims.put("roles", this.roles)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(this.username)
            .setExpiration(Date(Date().time + TimeUnit.HOURS.toMillis(expiration)))
            .signWith(sercetKey).compact()//.signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun addAuthentication(response: HttpServletResponse, user: UserDetails) {
        val jwt = user.createJwt()
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080")
        response.writer.write(jwt)
        response.writer.flush()
        response.writer.close()
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(header) ?: return null

        val tokenBody = Jwts.parserBuilder()
            .setSigningKey(sercetKey)
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