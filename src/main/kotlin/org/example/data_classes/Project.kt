package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.google.gson.annotations.SerializedName
import org.hibernate.annotations.DynamicUpdate
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Table(name="projects")

@Entity
@DynamicUpdate
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator::class, property="project_id")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    var project_id: Int? = null,

    @Column(name="title")
    var title: String? = null,

    @Column(name="description")
    var description: String? = null,

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    val creator: User? = null,

    @Column(name = "creator_id")
    var creator_id: Int? = null,

    @Column(name="start_datetime")
    var start_datetime: LocalDate? = null,

    @Column(name="end_datetime")
    var end_datetime: Date? = null,

    @Column(name="project_status")
    var project_status: Int? = null,

    @Column(name="price")
    var price: Double? = null,

    @SerializedName("category")
    @ManyToMany
    var categories: Set<Category>? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id")
    var comments: Set<Comment>? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id")
    var files: Set<File>? = null,


    @OneToMany
    @JoinColumn(name="project_id")
    var project_reaction: Set<User_reaction>? = null

){
    fun copy(project:Project){
        this.creator_id = project.creator_id ?: throw Exception("Creator id need for update!")
        this.project_id = project.project_id ?: throw Exception("Project id need for update!")
        if(project.title != null) this.title = project.title
        if(project.description != null) this.description = project.description
        if(project.start_datetime != null) this.start_datetime = project.start_datetime
        if(project.end_datetime != null) this.end_datetime = project.end_datetime
        if(project.project_status != null) this.project_status = project.project_status
        if(project.price != null) this.price = project.price
    }
}