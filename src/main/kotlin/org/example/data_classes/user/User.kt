package org.example.data_classes.user

import org.example.data_classes.Request
import org.example.data_classes.Role
import javax.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    val user_id: Int? = null,


    @Column(name="username")
    var username: String? = null,

    @Column(name="password")
    var password: String? = null,

    @Column(name="post")
    var post: String? = null,

    @Column(name="department")
    var department: String? = null,

    @Column(name="first_name")
    var first_name: String? = null,

    @Column(name="patronymic")
    var patronymic: String? = null,

    @Column(name="last_name")
    var last_name: String? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role")
    val role: Role? = null,

    @OneToMany
    @JoinColumn(name="creator_id")
    val requests: Set<Request>? = null
)