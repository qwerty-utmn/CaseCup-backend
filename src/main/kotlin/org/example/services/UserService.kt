package org.example.services

import net.bytebuddy.implementation.bytecode.Throw
import org.example.data_classes.Project
import org.example.data_classes.User
import org.example.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class UserService(private val userRepository: UserRepository) {

    fun all(): MutableIterable<User> = userRepository.findAll()

    fun add(user: User) = userRepository.save(user)

    fun remove(id:Int) = userRepository.deleteById(id)

    fun getById(id:Int): User? = userRepository.findByIdOrNull(id)

    fun edit(id:Int, user: User) {
        val old_user = userRepository.findByIdOrNull(id) ?: throw Exception("This user doesnt exist!")
        old_user.copy(user)
        userRepository.save(old_user)
    }

    fun signInUser(@Param("username") username: Any, @Param("password") password:Any): User {

        val user = userRepository.findAndCheckUser(username.toString(), password.toString())
        return user ?: throw Exception("User doesn't exist")
    }

}