package com.calendly.manager

import com.calendly.dal.UserDAL
import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserManager(private val userDAL: UserDAL) {

    fun createUser(emailId: String): User {
        return userDAL.save(User(emailId = emailId))
    }

    fun getUser(emailId: String): User? {
        return userDAL.findByEmailId(emailId)
    }
}