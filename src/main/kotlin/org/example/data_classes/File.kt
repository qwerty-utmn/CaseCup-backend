package org.example.data_classes

import javax.persistence.*


@Entity
@Table(name="files")
data class File(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    val file_id: Int?,

    @Column(name="content")
    val content: ByteArray,

    @ManyToOne
    @JoinColumn(name="request_id")
    val request: Request


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as File

        if (file_id != other.file_id) return false
        if (!content.contentEquals(other.content)) return false
        if (request != other.request) return false

        return true
    }

    override fun hashCode(): Int {
        var result = file_id ?: 0
        result = 31 * result + content.contentHashCode()
        result = 31 * result + request.hashCode()
        return result
    }
}