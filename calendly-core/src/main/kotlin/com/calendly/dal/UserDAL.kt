package com.calendly.dal


import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserDAL(private val userDao: UserDao) {
    fun findOrCreateUser(emailId: String): User =
        userDao.findByEmailId(emailId) ?: userDao.save(User(emailId = emailId))


}