package org.example.repositories

import org.example.data_classes.File
import org.springframework.data.repository.CrudRepository

interface FileRepository:CrudRepository<File, String> {
}