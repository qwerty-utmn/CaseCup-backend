package org.example.data_classes

import javax.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    val user_id: Int?,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role")
    val role: Role,

    @OneToMany
    @JoinColumn(name="creator_id")
    val requests: Set<Request>
)