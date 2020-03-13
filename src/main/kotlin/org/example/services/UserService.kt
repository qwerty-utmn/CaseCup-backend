package org.example.services

import org.example.data_classes.User
import org.example.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun all(): Iterable<User> = userRepository.findAll()

    fun add(user:User) = userRepository.save(user)

    fun getById(id:Int) = userRepository.findByIdOrNull(id)
}