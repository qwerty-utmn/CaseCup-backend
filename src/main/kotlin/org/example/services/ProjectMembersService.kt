package org.example.services

import org.example.data_classes.ProjectMembers
import org.example.data_classes.User_reaction
import org.example.data_classes.reactionsPK
import org.example.repositories.ProjectMemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception


//@Service
//class ProjectMembersService(private val projectMemberRepository: ProjectMemberRepository) {
//    fun add(member: ProjectMembers) = projectMemberRepository.save(member)
//
//    fun remove(reactionsPK: reactionsPK) = projectMemberRepository.deleteById(reactionsPK)
//
//    fun edit(reaction: User_reaction): User_reaction {
//        var old_reaction = projectMemberRepository.findByIdOrNull(reactionsPK(reaction.user_id, reaction.project_id)) ?: throw Exception("This reaction doesn't exist")
//        old_reaction.copy(reaction)
//        projectMemberRepository.save(old_reaction)
//        return old_reaction
//    }
//}