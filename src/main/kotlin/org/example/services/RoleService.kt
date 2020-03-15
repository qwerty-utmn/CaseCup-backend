package org.example.services

import org.example.data_classes.Role
import org.example.data_classes.user.User
import org.example.repositories.RoleRepository
import org.springframework.stereotype.Service


@Service
class RoleService(private val roleRepository: RoleRepository) {

    fun add(role: Role) = roleRepository.save(role)

    fun remove(role:String) = roleRepository.deleteById(role)


    fun all(): Iterable<Role> = roleRepository.findAll()
}