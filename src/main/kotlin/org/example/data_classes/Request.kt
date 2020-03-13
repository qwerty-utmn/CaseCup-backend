package org.example.data_classes

import java.util.*
import javax.persistence.*


@Entity
@Table(name="requests")
data class Request(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    val request_id: Int?,

    @OneToMany
    @JoinColumn(name="request_id")
    val comments: Set<Comment>,

    @OneToMany
    @JoinColumn(name="request_id")
    val files: Set<File>,

    @Column(name="title")
    val title: String,

    @Column(name="description")
    val description: String,

    @ManyToOne
    @JoinColumn(name="user_id")
    val creator_id: User,

    @Column(name="created_datetime")
    val created_datetime: Date,

    @Column(name="request_status")
    val request_status: Integer,

    @Column(name="category")
    val category: String
)