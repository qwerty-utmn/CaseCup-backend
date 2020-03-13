package org.example.data_classes

import javax.persistence.*

@Entity
@Table(name="comments")
data class Comment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    val comment_id: Int?,

    @Column(name="content")
    val content: String,

    @ManyToOne
    @JoinColumn(name="request_id")
    val request: Request


)