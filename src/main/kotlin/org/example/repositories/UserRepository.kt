package org.example.repositories

import org.example.data_classes.Project
import org.example.data_classes.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository: CrudRepository<User, Int> {

    fun findByUsername(name: String): User?

    fun existsByUsername(@Param("username") username: String): Boolean

    fun deleteByUsername(@Param("username") username: String)

    @Query(value="select p from Project as p where p.creator = :creator")
    fun findProjectsByUserId(@Param("creator") creator:User): Iterable<Project>

    @Query(nativeQuery = true, value = "SELECT likes_by_project_id(:id)")
    public fun likesByProjectId(@Param("id") project_id: Int):Int

    @Query(nativeQuery = true, value = "SELECT dislikes_by_project_id(:id)")
    public fun dislikesByProjectId(@Param("id") project_id: Int):Int

    @Query(value="select u from User as u where u.username = :username and u.password = :password ")
    fun findAndCheckUser(@Param("username") username: String, @Param("password") password:String): User?
}