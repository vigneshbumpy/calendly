package com.calendly.dal


import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class UserDAL(
    private val userDao: UserDao
) {
    fun findByEmailId(emailId: String): User? =
        userDao.findByEmailId(emailId)

    fun save(user: User): User = userDao.save(user)

}