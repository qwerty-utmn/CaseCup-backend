package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import net.minidev.json.annotate.JsonIgnore
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="comments")
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    var comment_id: Int? = null,

//    @Column(name="user_id")
//    var user_id: Int? = null,

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties("user_reactions", "department")
    var user: User? = null,

    @Column(name="project_id")
    var project_id: Int? = null,

    @Column(name="content")
    var content: String? = null,

    @Column(name="created_datetime")
    var created_datetime: LocalDate? = null

    )
{
    fun copy(comment:Comment){
        if(comment.comment_id!= null) this.comment_id = comment.comment_id
        if(comment.user!= null) this.user = comment.user
        if(comment.project_id!= null) this.project_id = comment.project_id
    }
}