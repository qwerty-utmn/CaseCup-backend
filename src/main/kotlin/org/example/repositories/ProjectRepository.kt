package org.example.repositories

import net.bytebuddy.matcher.MethodSortMatcher
import org.example.data_classes.Project
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


public interface ProjectRepository: CrudRepository<Project, Int> {


    @Query(value = "select pr from Project as pr where pr.title like :searchString ")
    fun selectWithFilter(sort: Sort, @Param("searchString") searchString: String):Iterable<Project>

    @Query(value = "select pr from Project as pr")
    fun selectWithFilterWithoutSearchStr(sort: Sort):Iterable<Project>

    @Query(nativeQuery = true, value = "SELECT likes_by_project_id(:id)")
    public fun likesByProjectId(@Param("id") project_id: Int):Int

    @Query(nativeQuery = true, value = "SELECT dislikes_by_project_id(:id)")
    public fun dislikesByProjectId(@Param("id") project_id: Int):Int

//    @Query(value = "select pr from Project as pr order by :filterField asc ")
//    fun selectWithFilterWithoutSearchStrASC(@Param("filterField") filterField: String):Iterable<Project>
//
//    @Query(value = "select pr from Project as pr order by :filterField desc ")
//    fun selectWithFilterWithoutSearchStrDESC(@Param("filterField") filterField: String):Iterable<Project>



}