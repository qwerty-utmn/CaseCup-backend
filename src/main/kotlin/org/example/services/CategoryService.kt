package org.example.services

import org.example.data_classes.Category
import org.example.data_classes.Role
import org.example.repositories.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun add(category: Category) = categoryRepository.save(category)

    fun remove(category_id:String) = categoryRepository.deleteById(category_id)

    fun getById(id:String) = categoryRepository.findByIdOrNull(id)

    fun all(): Iterable<Category> = categoryRepository.findAll()
}