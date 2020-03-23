package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.persistence.*


class reactionsPK(val user_id: Int? = null, val project_id: Int? = null):Serializable {
}


@Entity
@Table(name="users_reactions")
@IdClass(reactionsPK::class)
data class User_reaction (

    @Id
    @Column(name = "user_id")
    val user_id: Int? = null,

    @Id
    @Column(name = "project_id")
    val project_id: Int? = null,


    @Column(name="reaction")
    val reaction: Byte? = null

){

}