package com.calendly.manager

import com.calendly.dal.UserDAL
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserManager(private val userDAL: UserDAL) {
    fun getOrCreateUser(userId: String): User = userDAL.findOrCreateUser(userId)
}