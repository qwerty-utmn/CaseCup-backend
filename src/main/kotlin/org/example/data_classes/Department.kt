package org.example.data_classes

import com.fasterxml.jackson.annotation.*
import com.google.gson.annotations.SerializedName
import net.minidev.json.annotate.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*


@Entity
@Table(name="departments")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator::class, property="department_id")
data class Department(

    @SerializedName("department_id")
    @javax.persistence.Id
    @Column(name="department_id", nullable = false, columnDefinition = "VARCHAR(45)")
    var department_id: String? = null,
    @SerializedName("description")
    @Column(name="description")
    val description: String? = null,


    @OneToMany
    @JoinColumn(name="department_id")
    @JsonIgnoreProperties("department")
//    @JsonIgnore
    var users: Set<User>? = null
)