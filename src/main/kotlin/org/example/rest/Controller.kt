package org.example.rest

import io.swagger.annotations.Api
import org.example.data_classes.Request
import org.example.data_classes.Role
import org.example.data_classes.user.User
import org.example.services.RequestService
import org.example.services.RoleService
import org.example.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class ApiDoc{
    @RequestMapping(value = ["/api"], method = arrayOf(RequestMethod.GET))
    fun api() = "redirect:/api/swagger-ui.html";
}


@RestController
@RequestMapping("roles")
@Api(tags = arrayOf("Roles"))
class RolesController(private val roleService: RoleService) {

    @GetMapping
    fun getAllRoles() = roleService.all()

    @PostMapping("/create")
    fun createRequest(@RequestBody role: Role) = roleService.add(role)

    @DeleteMapping("{id}")
    fun removeRequest(@PathVariable roleName:String) = roleService.remove(roleName)


}



@RestController
@RequestMapping("users")
@Api(tags = arrayOf("User"))
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllRoles() = userService.all()

    @GetMapping("{id}")
    fun getOneUser(@PathVariable id:Int) =userService.getById(id)

    @PostMapping("/create")
    fun createRequest(@RequestBody user: User) = userService.add(user)

    @DeleteMapping("{id}")
    fun removeRequest(@PathVariable id:Int) = userService.remove(id)
}

@RestController
@RequestMapping("requests")
@Api(tags = arrayOf("Request"))
class RequestController(private val requestService: RequestService) {

    @GetMapping
    fun getAllRequests() = requestService.all()

    @GetMapping("{id}")
    fun getOneRequest(@PathVariable id:Int) = requestService.getById(id)

    @PostMapping("/create")
    fun createRequest(@RequestBody request: Request) = requestService.add(request)

    @PutMapping("{id}")
    fun updateRequest(@PathVariable id:Int, request: Request) = requestService.edit(id, request)

    @DeleteMapping("{id}")
    fun removeRequest(@PathVariable id:Int) = requestService.remove(id)
}