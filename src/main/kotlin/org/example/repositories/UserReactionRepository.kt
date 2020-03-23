package org.example.repositories

import org.example.data_classes.User_reaction
import org.example.data_classes.reactionsPK
import org.springframework.data.repository.CrudRepository

interface UserReactionRepository: CrudRepository<User_reaction, reactionsPK> {
}