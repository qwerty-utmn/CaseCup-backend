package org.example.repositories

import org.example.data_classes.Project
import org.springframework.data.repository.CrudRepository

interface ProjectRepository: CrudRepository<Project, Int> {
}