package org.example.repositories

import org.example.data_classes.Department
import org.springframework.data.repository.CrudRepository

interface DepartmentRepository: CrudRepository<Department, String> {
}