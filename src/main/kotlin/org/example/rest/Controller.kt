package org.example.rest

import io.swagger.annotations.Api
import org.example.data_classes.*
import org.example.services.*
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@Api(tags = arrayOf("Documentation"))
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
@RequestMapping("users", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
@Api(tags = arrayOf("User"))
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllRoles() = userService.all()

    @GetMapping("{id}")
    fun getOneUser(@PathVariable id:Int):ResponseEntity<*>{
        var user: User? = userService.getById(id) ?: return ResponseEntity.badRequest().body("The user doesn't exist")
        return ResponseEntity.ok().body(user)
    }

    @PostMapping("/create")
    fun createRequest(@RequestBody user: User) = userService.add(user)

    @PutMapping("{id}")
    fun updateRequest(@PathVariable id:Int, @RequestBody user: User) = userService.edit(id, user)

    @DeleteMapping("{id}")
    fun removeRequest(@PathVariable id:Int) = userService.remove(id)
}


@RestController
@RequestMapping("reactions")
@Api(tags = arrayOf("Reactions"))
class ReactionController(private val reactionService: UserReactionService){

    @PostMapping("/create")
    fun createProject(@RequestBody reaction: User_reaction) = reactionService.add(reaction)

}


@RestController
@RequestMapping("projects")
@Api(tags = arrayOf("Projects"))
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    fun getAllProjects() = projectService.all()

    @GetMapping("{id}")
    fun getOneProject(@PathVariable id:Int):ResponseEntity<*>{
        var project: Project? = projectService.getById(id) ?: return ResponseEntity.badRequest().body("The request doesn't exist")
        return ResponseEntity.ok().body(project)
    }

    @PostMapping("/create")
    fun createProject(@RequestBody project: Project) = projectService.add(project)

    @PutMapping("{id}")
    fun updateProject(@PathVariable id:Int,@RequestBody project: Project) = projectService.edit(id, project)

    @DeleteMapping("{id}")
    fun removeProject(@PathVariable id:Int) = projectService.remove(id)
}


@RestController
@RequestMapping("departments")
@Api(tags = arrayOf("Departments"))
class DepartmentController(private val departmentService: DepartmentService) {

    @GetMapping
    fun getAllDepartments() = departmentService.all()

    @GetMapping("{id}")
    fun getOneDepartments(@PathVariable id:String):ResponseEntity<*>{
        var department: Department? = departmentService.getById(id) ?: return ResponseEntity.badRequest().body("The request doesn't exist")
        return ResponseEntity.ok().body(department)
    }

    @PostMapping("/create")
    fun createDepartment(@RequestBody department: Department) = departmentService.add(department)

    @PutMapping("{id}")
    fun updateDepartment(@PathVariable id:String,@RequestBody department: Department) = departmentService.edit(id, department)

    @DeleteMapping("{id}")
    fun removeDepartment(@PathVariable id:String) = departmentService.remove(id)
}

@RestController
@RequestMapping("comments", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
@Api(tags = arrayOf("Comment"))
class CommentController(private val commentService: CommentService) {

    @GetMapping
    fun getAllComments() = commentService.all()

    @GetMapping("{id}")
    fun getOneComment(@PathVariable id:Int):ResponseEntity<*>{
        var comment: Comment? = commentService.getById(id) ?: return ResponseEntity.badRequest().body("The comment doesn't exist")
        return ResponseEntity.ok().body(comment)
    }

    @PostMapping("/create")
    fun createComment(@RequestBody comment: Comment) = commentService.add(comment)

    @PutMapping("{id}")
    fun updateComment(@PathVariable id:Int,@RequestBody comment: Comment) = commentService.edit(id, comment)

    @DeleteMapping("{id}")
    fun removeComment(@PathVariable id:Int) = commentService.remove(id)
}
