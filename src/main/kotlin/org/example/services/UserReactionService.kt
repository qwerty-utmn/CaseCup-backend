package org.example.services

import org.example.data_classes.User_reaction
import org.example.repositories.UserReactionRepository
import org.springframework.stereotype.Service

@Service
class UserReactionService(private val userReactionRepository: UserReactionRepository) {
    fun add(reaction: User_reaction) = userReactionRepository.save(reaction)
}