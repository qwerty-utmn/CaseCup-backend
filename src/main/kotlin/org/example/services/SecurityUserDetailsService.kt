package org.example.services

import org.example.data_classes.User
import org.example.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class SecurityUserDetailsService(private val userRepository: UserRepository): UserDetailsService {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    override fun loadUserByUsername(s: String): UserDetails {
        val user: User = userRepository.findByUsername(s) ?: throw(UsernameNotFoundException("Username not found") as Throwable)
        return org.springframework.security.core.userdetails.User(user.username, passwordEncoder?.encode(user.password), arrayListOf())
    }
}