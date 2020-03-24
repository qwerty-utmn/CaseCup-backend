package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.google.gson.annotations.SerializedName
import org.springframework.data.annotation.Id
import javax.persistence.*

@Entity
@Table(name="roles")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator::class, property="role")

data class Role(
    @SerializedName("role")
    @javax.persistence.Id
    @Column(name="role", nullable = false,columnDefinition = "VARCHAR(45)")
    var role: String? = "",
    @SerializedName("description")
    @Column(name="description")
    val description: String? = null,


    @OneToMany
    @JoinColumn(name="role")
    var users: Set<User>? = null
)