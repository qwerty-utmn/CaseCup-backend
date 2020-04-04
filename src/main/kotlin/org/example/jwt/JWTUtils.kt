package org.example.jwt

import com.google.gson.Gson
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.IOException
import io.jsonwebtoken.security.Keys
import org.example.data_classes.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import java.io.BufferedReader
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
        response.setHeader("Access-Control-Allow-Origin", "*") //"http://localhost:8080")//
        response.setHeader("Access-Control-Allow-Methods", "POST")
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
        response.writer.write(jwt)
        response.writer.flush()
        response.writer.close()
    }

    fun getUserNameByToken(request: HttpServletRequest): String {
//        val token = getBody(request)//request.getHeader(header)
        val token = Gson().fromJson(getBody(request), Token::class.java)

        val tokenBody = Jwts.parserBuilder()
            .setSigningKey(secretKey)//.getBytes(Charset.forName("UTF-8")
            .build()
            .parseClaimsJws(token.token)
            .body
        return tokenBody.subject
    }


    fun getBody(req: HttpServletRequest): String? {
        var body = ""
        if (req.getMethod().equals("POST")) {
            val sb = StringBuilder()
            var bufferedReader: BufferedReader? = null
            try {
                bufferedReader = req.getReader()
                val charBuffer = CharArray(128)
                var bytesRead: Int
                while (bufferedReader.read(charBuffer).also { bytesRead = it } != -1) {
                    sb.append(charBuffer, 0, bytesRead)
                }
            } catch (ex: IOException) { // swallow silently -- can't get body, won't
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (ex: IOException) { // swallow silently -- can't get body, won't
                    }
                }
            }
            body = sb.toString()
        }
        return body
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