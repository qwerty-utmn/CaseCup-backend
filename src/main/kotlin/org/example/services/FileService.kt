package org.example.services

import org.example.data_classes.File
import org.example.repositories.FileRepository
import org.example.repositories.ProjectRepository
import org.springframework.stereotype.Service


@Service
class FileService(private val fileRepository: FileRepository) {

    fun add(file: File) = fileRepository.save(file)

    fun getFiles(project_id: Int) = fileRepository.findByProjectId(project_id)

    fun getAll() = fileRepository.findAll()


}