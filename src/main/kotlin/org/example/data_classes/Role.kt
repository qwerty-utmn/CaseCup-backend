package org.example.data_classes

import com.google.gson.annotations.SerializedName
import org.springframework.data.annotation.Id
import javax.persistence.*

@Entity
@Table(name="roles")
data class Role(
    @SerializedName("role")
    @javax.persistence.Id
    @Column(name="role", nullable = false, columnDefinition = "VARCHAR(45)", unique = true)
    var role: String = "",
    @SerializedName("description")
    @Column(name="description")
    val description: String? = null
)