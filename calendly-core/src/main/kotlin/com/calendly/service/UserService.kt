package com.calendly.service

import com.calendly.dal.UserDAL
import com.calendly.dao.UserDao
import com.calendly.manager.UserManager
import com.calendly.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userManager: UserManager
) {

    fun getUser(emailId: String): User? {
        return userManager.getUser(emailId)
    }

    @Transactional
    fun saveUser(emailId: String): User {
        return userManager.createUser(emailId)
    }
}