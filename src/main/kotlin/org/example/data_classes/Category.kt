package org.example.data_classes

import com.google.gson.annotations.SerializedName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name="categories")
data class Category(
    @SerializedName("category_id")
    @javax.persistence.Id
    @Column(name="category_id", nullable = false, columnDefinition = "VARCHAR(45)", unique = true)
    val category_id: String? = null,

    @SerializedName("description")
    @Column(name="description")
    val description: String? = null,

    @SerializedName("requests")
    @ManyToMany
    val requestsList: Set<Project>? = null

)