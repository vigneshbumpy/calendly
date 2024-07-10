package com.calendly.service

import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDao: UserDao
) {

    fun getUser(emailId: String): User? {
        return userDao.findByEmailId(emailId)
    }

    fun saveUser(user: User): User {
        return userDao.save(user)
    }
}