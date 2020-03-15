package org.example.services

import org.example.data_classes.Request
import org.example.data_classes.user.User
import org.example.repositories.RequestRepository
import org.example.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class RequestService(private val requestRepository: RequestRepository) {

    fun all(): Iterable<Request> = requestRepository.findAll()

    fun add(request: Request) = requestRepository.save(request)

    fun edit(id:Int, request: Request) = requestRepository.save(request.copy(request_id = id))

    fun getById(id:Int) = requestRepository.findByIdOrNull(id)

    fun remove(id:Int) = requestRepository.deleteById(id)
    

}