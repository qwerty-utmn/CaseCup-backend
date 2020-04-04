package org.example.services

import org.example.data_classes.Project
import org.example.data_classes.User_reaction
import org.example.data_classes.reactionsPK
import org.example.repositories.UserReactionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class UserReactionService(private val userReactionRepository: UserReactionRepository) {
    fun add(reaction: User_reaction) = userReactionRepository.save(reaction)

    fun remove(reactionsPK: reactionsPK) = userReactionRepository.deleteById(reactionsPK)

    fun edit(reaction: User_reaction):User_reaction{
        var old_reaction = userReactionRepository.findByIdOrNull(reactionsPK(reaction.user_id, reaction.project_id)) ?: throw Exception("This reaction doesn't exist")
        old_reaction.copy(reaction)
        userReactionRepository.save(old_reaction)
        return old_reaction
    }
}