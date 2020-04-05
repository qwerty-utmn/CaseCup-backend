package org.example.data_classes

import com.fasterxml.jackson.annotation.*
import com.google.gson.annotations.SerializedName
import org.example.repositories.ProjectRepository
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Formula
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
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

    @ManyToOne
    @JoinColumn(name="creator_id")
    var creator: User? = null,

//    @Column(name = "creator_id")
//    var creator_id: Int? = null,

    @Column(name="start_datetime")
    var start_datetime: LocalDate? = null,

    @Column(name="end_datetime")
    var end_datetime: Date? = null,

    @Column(name="project_status")
    var project_status: Int? = 0,

    @Column(name="price")
    var price: Double? = null,

    @SerializedName("category")
    @ManyToMany
    @JoinTable(name = "project_categories",
        joinColumns = arrayOf(JoinColumn(name = "project_id")),//, referencedColumnName = "category_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "category_id"))//, referencedColumnName = "project_id"))
    )
//    @JsonBackReference
    @JsonIgnoreProperties("projects")
    var categories: List<Category>? = null,

    @OneToMany
    @JoinColumn(name="project_id")
//    @JsonIgnoreProperties("user", "project_id")
    var comments: List<Comment>? = null,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id")
    var files: Set<File>? = null,

    @ManyToMany
    @JoinTable(name = "project_members",
        joinColumns = arrayOf(JoinColumn(name = "project_id")),//, referencedColumnName = "category_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "user_id"))//, referencedColumnName = "project_id"))
    )
    var project_members: MutableList<User>? = mutableListOf<User>(),

    @OneToMany
    @JoinColumn(name="project_id")
    var project_reaction: Set<User_reaction>? = null,

    @Transient
    var likes: Int? = 0,

    @Transient
    var dislikes: Int? = 0

){
    fun copy(project:Project){
        if(project.creator != null) this.creator = project.creator
        if(project.project_id != null) this.project_id = project.project_id
        if(project.title != null) this.title = project.title
        if(project.description != null) this.description = project.description
        if(project.start_datetime != null) this.start_datetime = project.start_datetime
        if(project.end_datetime != null) this.end_datetime = project.end_datetime
        if(project.project_status != null) this.project_status = project.project_status
        if(project.price != null) this.price = project.price

        if(project.categories != null) this.categories = project.categories
//        if(project.comments != null) throw Exception("Пока не сделал для обновления комментариев")//this.categories = project.categories
//        if(project.files != null) throw Exception("Пока не сделал для обновления файлов")//this.categories = project.categories
    }
}