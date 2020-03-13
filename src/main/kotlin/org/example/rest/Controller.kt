package org.example.rest

import org.example.services.RoleService
import org.example.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("roles")
class RolesController(private val roleService: RoleService) {

    @GetMapping
    fun getAllRoles() = roleService.all()
}



@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllRoles() = userService.all()

    @GetMapping("{id}")
    fun getOneUser(@PathVariable id:Int) =userService.getById(id)
}