package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import javax.persistence.*


class memberPK(val user_id: Int? = null, val project_id: Int? = null): Serializable {
}


@Entity
@Table(name="project_members")
@IdClass(memberPK::class)
data class ProjectMembers (

    @Id
    @Column(name = "user_id")
    val user_id: Int? = null,

    @Id
    @Column(name = "project_id")
    val project_id: Int? = null,

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("user_reactions", "department", "role")
    var user: User? = null,

    @Column(name="role")
    var role: String? = null

){
    fun copy(member: ProjectMembers){
        this.role = member.role
    }
}