package org.example.repositories

import org.example.data_classes.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository:CrudRepository<Category, String> {
}