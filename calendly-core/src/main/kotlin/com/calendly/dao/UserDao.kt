package com.calendly.dao

import com.calendly.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : JpaRepository<User, Long> {
    fun findByEmailId(emailId: String): User?

}