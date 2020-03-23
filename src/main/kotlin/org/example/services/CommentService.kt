package org.example.services

import org.example.data_classes.Comment
import org.example.data_classes.Department
import org.example.repositories.CommentRepository
import org.example.repositories.DepartmentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository)
{

    fun all(): Iterable<Comment> = commentRepository.findAll()

    fun add(department: Comment) = commentRepository.save(department)

    fun edit(id: Int, comment: Comment) = commentRepository.save(comment.copy(comment_id = id))

    fun getById(id: Int) = commentRepository.findByIdOrNull(id)

    fun remove(id: Int) = commentRepository.deleteById(id)
}