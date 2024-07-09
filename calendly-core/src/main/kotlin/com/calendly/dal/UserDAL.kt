package com.calendly.dal


import com.calendly.dao.UserRepository
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserDAL(private val userRepository: UserRepository) {
    fun findOrCreateUser(userId: String): User =
        userRepository.findByUserId(userId) ?: userRepository.save(User(userId = userId))
}