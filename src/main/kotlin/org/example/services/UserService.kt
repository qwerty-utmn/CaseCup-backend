package org.example.services

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

    fun getByUsername(username: String): User? = userRepository.findByUsername(username)

    fun edit(id:Int, user: User):User {
        val old_user = userRepository.findByIdOrNull(id) ?: throw Exception("This user doesnt exist!")
        old_user.copy(user)
        userRepository.save(old_user)
        return old_user
    }

    fun getAllProjectByUserId(id:Int):Iterable<Project> {
        val user = User(user_id = id)
        val projects = userRepository.findProjectsByUserId(user)
        projects.map { e ->
            e.likes = userRepository.likesByProjectId(e.project_id!!)
            e.dislikes = userRepository.dislikesByProjectId(e.project_id!!)
        }
        return projects
    }

    fun signInUser(@Param("username") username: Any, @Param("password") password:Any): User {

        val user = userRepository.findAndCheckUser(username.toString(), password.toString())
        return user ?: throw Exception("User doesn't exist")
    }

}