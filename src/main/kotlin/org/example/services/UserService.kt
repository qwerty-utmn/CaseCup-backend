package org.example.services

import org.example.data_classes.user.User
import org.example.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class UserService(private val userRepository: UserRepository) {

    fun all(): Iterable<User> = userRepository.findAll()

    fun add(user: User) = userRepository.save(user)

    fun remove(id:Int) = userRepository.deleteById(id)

    fun getById(id:Int) = userRepository.findByIdOrNull(id)

    fun signInUser(@Param("username") username: Any, @Param("password") password:Any):User{

        val user = userRepository.findAndCheckUser(username.toString(), password.toString())
        return user ?: throw Exception("User doesn't exist")
    }

}