package org.example.services

import org.example.data_classes.Project
import org.example.repositories.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class ProjectService(private val projectRepository: ProjectRepository) {

    fun all(): Iterable<Project> = projectRepository.findAll()

    fun add(project: Project) = projectRepository.save(project)

    fun edit(id:Int, project: Project){
        var old_project = projectRepository.findByIdOrNull(id) ?: throw Exception("This project doesn't exist")
        println(old_project)
        old_project.copy(project)
        println(old_project)
        projectRepository.save(old_project)
    }

    fun getById(id:Int) = projectRepository.findByIdOrNull(id)

    fun remove(id:Int) = projectRepository.deleteById(id)
    

}