package com.calendly.manager

import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserManager(private val userDao: UserDao) {

    fun createUser(emailId: String): User {
        return userDao.save(User(emailId = emailId))
    }

    fun getUser(emailId: String): User? {
        return userDao.findByEmailId(emailId)
    }
}