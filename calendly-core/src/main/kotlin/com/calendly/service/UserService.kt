package com.calendly.service

import com.calendly.dal.UserDAL
import com.calendly.dao.UserDao
import com.calendly.manager.UserManager
import com.calendly.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User service
 *
 * @property userManager
 * @constructor Create empty User service
 */
@Service
class UserService(
    private val userManager: UserManager
) {

    /**
     * Get user
     *
     * @param emailId
     * @return
     */
    fun getUser(emailId: String): User? {
        return userManager.getUser(emailId)
    }

    /**
     * Save user
     *
     * @param emailId
     * @return
     */
    @Transactional
    fun saveUser(emailId: String): User {
        return userManager.createUser(emailId)
    }
}