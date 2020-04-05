package org.example.data_classes

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*


@Entity
@Table(name="files")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator::class, property="file_id")
data class File(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    val file_id: Int? = null,

    @Column(name="file_name")
    var file_name: String? = null,

//    @ManyToOne
//    @JoinColumn(name="project_id")
    @Column(name="project_id")
    val project_id: Int? = null


) {

}