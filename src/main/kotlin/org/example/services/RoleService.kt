package org.example.services

import org.example.data_classes.Role
import org.example.repositories.RoleRepository
import org.springframework.stereotype.Service


@Service
class RoleService(private val roleRepository: RoleRepository) {

    fun all(): Iterable<Role> = roleRepository.findAll()
}