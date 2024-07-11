package com.calendly.manager

import com.calendly.dal.UserDAL
import com.calendly.dao.UserDao
import com.calendly.model.User
import org.springframework.stereotype.Component

/**
 * User manager
 *
 * @property userDAL
 * @constructor Create empty User manager
 */
@Component
class UserManager(private val userDAL: UserDAL) {

    /**
     * Create user
     *
     * @param emailId
     * @return
     */
    fun createUser(emailId: String): User {
        return userDAL.save(User(emailId = emailId))
    }

    /**
     * Get user
     *
     * @param emailId
     * @return
     */
    fun getUser(emailId: String): User? {
        return userDAL.findByEmailId(emailId)
    }
}