package org.example.data_classes

import com.fasterxml.jackson.annotation.*
import org.example.data_classes.Project
import org.example.data_classes.Role
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.lang.Exception
import javax.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    var user_id: Int? = null,


    @Column(name="username")
    var username: String? = null,

    @JsonIgnore
    @Column(name="password")
    var password: String? = null,

    @ManyToOne
    @JoinColumn(name="role")
    var role: Role? = null,

    @Column(name="name")
    var name: String? = null,

    @Column(name="surname")
    var surname: String? = null,

    @Column(name="middlename")
    var middlename: String? = null,

    //@Column(name="post")
    //var post: String? = null,

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("description", "users")
    var department: Department? = null,
//
//    @Column(name="department_id")
//    var department_id: String? = null,

    @Column(name="user_photo")
    var user_photo: ByteArray? = byteArrayOf(),

    @OneToMany
    @JoinColumn(name="user_id" )
//    @JsonBackReference
    @JsonIgnore
    var projects: Set<Project>? = null,


    @OneToMany
    @JoinColumn(name="user_id")
    var user_reactions: Set<User_reaction>? = null,

    @ManyToMany(mappedBy = "project_members")
    @JsonIgnore
    val involved_projects: List<Project>? = null,//listOf()

    @OneToMany
    @JoinColumn(name="user_id")
    @JsonIgnore
    var comments: Set<Comment>? = null


    ) {

    fun copy(user:User){
        if(user.user_id != null) this.user_id = user.user_id
        if(user.username != null) this.username = user.username
        if(user.password != null) this.password = user.password
        if(user.role != null) this.role = user.role
        if(user.name != null) this.name = user.name
        if(user.surname != null) this.surname = user.surname
        if(user.middlename != null) this.middlename = user.middlename
        if(user.department != null) this.department = user.department
        if(user.user_photo != null) this.user_photo = user.user_photo
        if(user.projects != null) this.projects = user.projects
        if(user.user_reactions != null) this.user_reactions = user.user_reactions
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (user_id != other.user_id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (role != other.role) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (middlename != other.middlename) return false
        if (department != other.department) return false
        if (user_photo != null) {
            if (other.user_photo == null) return false
            if (!user_photo!!.contentEquals(other.user_photo!!)) return false
        } else if (other.user_photo != null) return false
        if (projects != other.projects) return false
        if (user_reactions != other.user_reactions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user_id ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (role?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        result = 31 * result + (middlename?.hashCode() ?: 0)
        result = 31 * result + (department?.hashCode() ?: 0)
        result = 31 * result + (user_photo?.contentHashCode() ?: 0)
        result = 31 * result + (projects?.hashCode() ?: 0)
        result = 31 * result + (user_reactions?.hashCode() ?: 0)
        return result
    }


}