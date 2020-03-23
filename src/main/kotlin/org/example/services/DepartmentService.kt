package org.example.services

import org.example.data_classes.Department
import org.example.data_classes.Project
import org.example.repositories.DepartmentRepository
import org.example.repositories.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DepartmentService(private val departmentRepository: DepartmentRepository)
{

    fun all(): Iterable<Department> = departmentRepository.findAll()

    fun add(department: Department) = departmentRepository.save(department)

    fun edit(id: String, department: Department) = departmentRepository.save(department.copy(department_id = id))

    fun getById(id: String) = departmentRepository.findByIdOrNull(id)

    fun remove(id: String) = departmentRepository.deleteById(id)
}