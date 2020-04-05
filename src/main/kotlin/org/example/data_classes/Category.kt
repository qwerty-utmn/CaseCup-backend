package org.example.data_classes

import com.fasterxml.jackson.annotation.*
import com.google.gson.annotations.SerializedName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name="categories")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator::class, property="category_id")
data class Category(
    @SerializedName("category_id")
    @javax.persistence.Id
    @Column(name="category_id", nullable = false, columnDefinition = "VARCHAR(45)")
    val category_id: String? = null,

    @SerializedName("description")
    @Column(name="description")
    val description: String? = null,


    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties("categories", "creator")
    val projects: List<Project>? = null//listOf()

)