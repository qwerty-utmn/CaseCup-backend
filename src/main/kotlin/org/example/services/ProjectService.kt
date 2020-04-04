package org.example.services

import org.example.data_classes.Project
import org.example.repositories.ProjectRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.lang.Exception
import java.lang.StringBuilder
import javax.persistence.OrderBy


@Service
class ProjectService(private val projectRepository: ProjectRepository) {

    fun all(): Iterable<Project> = projectRepository.findAll()

    fun allWithFilter(filterField: String, sort: String,searchString: String?): Iterable<Project>{
        var projects: Iterable<Project>?
        projects = if(searchString != "" && searchString != null){
            val search = "%$searchString%"
            when(sort.toLowerCase()) {
                "desc" -> projectRepository.selectWithFilter(Sort.by(Sort.Direction.ASC, filterField), search)
                else ->projectRepository.selectWithFilter(Sort.by(Sort.Direction.DESC, filterField), search)
            }
        } else
            when(sort.toLowerCase()) {
                "desc" -> projectRepository.selectWithFilterWithoutSearchStr(Sort.by(Sort.Direction.ASC, filterField))
                else ->projectRepository.selectWithFilterWithoutSearchStr(Sort.by(Sort.Direction.DESC, filterField))
            }

        return projects.sortedBy { searchString}.reversed()
    }

    fun add(project: Project) = projectRepository.save(project)

    fun edit(id:Int, project: Project):Project{
        var old_project = projectRepository.findByIdOrNull(id) ?: throw Exception("This project doesn't exist")
        old_project.copy(project)
        projectRepository.save(old_project)
        return old_project
    }

    fun getById(id:Int) = projectRepository.findByIdOrNull(id)

    fun remove(id:Int) = projectRepository.deleteById(id)
    

}