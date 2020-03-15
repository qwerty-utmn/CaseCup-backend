package org.example.repositories

import org.example.data_classes.Request
import org.springframework.data.repository.CrudRepository

interface RequestRepository: CrudRepository<Request, Int> {
}