package org.example.repositories

import org.example.data_classes.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository: CrudRepository<Comment, Int> {
}