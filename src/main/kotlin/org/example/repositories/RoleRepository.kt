package org.example.repositories

import org.example.data_classes.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository: CrudRepository<Role, String> {
}