package org.example.data_classes

import org.apache.catalina.mbeans.UserMBean
import javax.persistence.*

@Entity
@Table(name="users_reactions")
data class User_reaction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reaction_id: Int?,

    @OneToOne
    @JoinColumn(name="user_id")
    val user: User,

    @OneToOne
    @JoinColumn(name="request_id")
    val request: Request,

    @Column(name="reaction")
    val reaction: Byte

)