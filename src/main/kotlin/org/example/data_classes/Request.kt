package org.example.data_classes

import org.example.data_classes.user.User
import java.util.*
import javax.persistence.*


@Entity
@Table(name="requests")
data class Request(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    val request_id: Int? = 0,

    @OneToMany
    @JoinColumn(name="request_id")
    val comments: Set<Comment>? = null,

    @OneToMany
    @JoinColumn(name="request_id")
    val files: Set<File>? = null,

    @Column(name="title")
    val title: String? = null,

    @Column(name="description")
    val description: String? = null,


    @ManyToOne
    @JoinColumn(name="user_id")
    val creator_id: User? = null,

    @Column(name="created_datetime")
    val created_datetime: Date? = null,

    @Column(name="request_status")
    val request_status: Integer? = null,

    @Column(name="category")
    val category: String? = null
)