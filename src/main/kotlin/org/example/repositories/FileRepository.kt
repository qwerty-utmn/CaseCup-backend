package org.example.repositories

import org.example.data_classes.File
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface FileRepository:CrudRepository<File, Int> {


    @Query(value = "select files from File as files where files.project_id = :project_id ")
    fun findByProjectId(@Param("project_id") project_id:Int) :Iterable<File>

}