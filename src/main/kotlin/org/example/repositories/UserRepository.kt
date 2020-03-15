package org.example.repositories

import org.example.data_classes.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface UserRepository: CrudRepository<User, Int> {

    fun findByUsername(name: String): User?

    fun existsByUsername(@Param("username") username: String): Boolean

    fun deleteByUsername(@Param("username") username: String)
}