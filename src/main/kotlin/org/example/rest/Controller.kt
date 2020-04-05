package org.example.rest

import io.swagger.annotations.Api
import org.example.data_classes.*
import org.example.jwt.JWTUtils
import org.example.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.ServletContext
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest


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

    @PostMapping("/by_token")
    fun getUserByToken(req: ServletRequest):User{
        val usernameFromToken = JWTUtils.getUserNameByToken(req as HttpServletRequest)
        val user = userService.getByUsername(usernameFromToken) ?: throw Exception("Such user doesn't exist")
        return user
    }

    @PostMapping("/create")
    fun createRequest(@RequestBody user: User) = userService.add(user)

    @PutMapping("{id}")
    fun updateRequest(@PathVariable id:Int, @RequestBody user: User) = userService.edit(id, user)

    @DeleteMapping("{id}")
    fun removeRequest(@PathVariable id:Int) = userService.remove(id)

    @GetMapping("/{id}/projects")
    fun getProjects(@PathVariable id:Int) = userService.getAllProjectByUserId(id)
}


@RestController
@RequestMapping("reactions")
@Api(tags = arrayOf("Reactions"))
class ReactionController(private val reactionService: UserReactionService){

    @PostMapping("/create")
    fun createReaction(@RequestBody reaction: User_reaction) = reactionService.add(reaction)

    @PutMapping("/update")
    fun updateReaction(@RequestBody reaction: User_reaction) = reactionService.edit(reaction)

    @DeleteMapping("/remove")
    fun deleteReaction(@RequestBody primaryReaction: reactionsPK) = reactionService.remove(primaryReaction)
}


@RestController
@RequestMapping("projects")
@Api(tags = arrayOf("Projects"))
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    fun getAllProjects(): Iterable<Project> = projectService.all()


    @GetMapping("/by")
    fun getProjects(@RequestParam filter: String, @RequestParam sort:String, @RequestParam(required = false) search:String?): Iterable<Project>
    {
        val projects = projectService.allWithFilter(filter, sort, search)
        return projects
    }


    @GetMapping("{id}")
    fun getOneProject(@PathVariable id:Int):ResponseEntity<*>{
        var project: Project? = projectService.getById(id) ?: return ResponseEntity.badRequest().body("The request doesn't exist")
        return ResponseEntity.ok().body(project)
    }

    @PostMapping("/create")
    fun createProject(@RequestBody project: Project)
    {
        println(project)
        projectService.add(project)
    }

    @PutMapping("{id}")
    fun updateProject(@PathVariable id:Int,@RequestBody project: Project) = projectService.edit(id, project)

    @DeleteMapping("{id}")
    fun removeProject(@PathVariable id:Int) = projectService.remove(id)

    @PostMapping("/{project_id}/add_member")
    fun addMember(@PathVariable project_id:Int, @RequestBody member: User) = projectService.addMemberInProject(project_id, member)

    @DeleteMapping("/{project_id}/remove_member")
    fun removeMember(@PathVariable project_id:Int, @RequestBody member: User) = projectService.removeMemberFromProject(project_id, member)
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
@RequestMapping("file")
@Api(tags= arrayOf("Files"))
class FileController(private val fileService: FileService){

//    @Autowired
//    private val request: HttpServletRequest? = null
    @Autowired
    var context: ServletContext? = null

    @Autowired
    private val env: Environment? = null
    val uploadsDir = "/files/"


    @PostMapping("/create")
    fun createFile(@RequestPart("file") files: List<MultipartFile>, @RequestPart(value="file_info") fileInfo:org.example.data_classes.File){
        if(!files.isEmpty()){

            //env?.getProperty("upload.path")
            val realPathUploads = context!!.getRealPath(uploadsDir)
            if(!File(realPathUploads!!).exists()){
                File(realPathUploads).mkdir()
            }
            println("Путь $realPathUploads")
            files.forEach{

                val filePath = realPathUploads + it.originalFilename
                val file = File(filePath)
                it.transferTo(file)

                fileInfo.file_name = it.originalFilename
                fileService.add(fileInfo)
            }
        }

    }

    @GetMapping("/by")
    fun getFilesByProjectId(@RequestParam project_id:Int)  = fileService.getFiles(project_id)

//        val listFiles: MutableList<String> = mutableListOf()
//        filesInfo.forEach{
//            //val file = File(realPathUploads+it.file_name)
//            listFiles.add(//InputStreamResource(FileInputStream(file)))
//        }
//        val headers = HttpHeaders()
//        headers["Content-Type"] = "multipart/form-data"




    @PostMapping
    fun getFile(@RequestBody file: org.example.data_classes.File): ResponseEntity<*>{
        val realPathUploads = context!!.getRealPath(uploadsDir)
        val path = realPathUploads+file.file_name
        val send_file = File(path )

        println("path - $path")
        println("file name - ${send_file.name}")
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${send_file.name}\"")
            .body(send_file)
    }

    @GetMapping("/all")
    fun getAllFiles() = fileService.getAll()
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


@RestController
@RequestMapping("categories", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
@Api(tags = arrayOf("Categories"))
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategory() = categoryService.all()

//    @GetMapping("{id}")
//    fun getOneComment(@PathVariable category_id:String):ResponseEntity<*>{
//        var category: Category? = categoryService.getById(category_id) ?: return ResponseEntity.badRequest().body("The comment doesn't exist")
//        return ResponseEntity.ok().body(comment)
//    }

    @PostMapping("/create")
    fun createCategory(@RequestBody category: Category) = categoryService.add(category)


    @DeleteMapping("{id}")
    fun removeCategory(@PathVariable category_id:String) = categoryService.remove(category_id)
}

