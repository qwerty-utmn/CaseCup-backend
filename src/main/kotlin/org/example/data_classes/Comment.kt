package org.example.data_classes

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="comments")
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    var comment_id: Int? = null,

    @Column(name="user_id")
    var user_id: Int? = null,

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
        if(comment.user_id!= null) this.user_id = comment.user_id
        if(comment.project_id!= null) this.user_id = comment.user_id
    }
}