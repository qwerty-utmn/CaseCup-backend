package org.example.repositories

import org.example.data_classes.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {
}