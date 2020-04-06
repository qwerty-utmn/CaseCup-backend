package org.example.repositories

import org.example.data_classes.ProjectMembers
import org.example.data_classes.memberPK
import org.springframework.data.repository.CrudRepository

interface ProjectMemberRepository: CrudRepository<ProjectMembers, memberPK> {

}