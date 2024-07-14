package com.calendly.service

import com.calendly.manager.UserManager
import com.calendly.model.CalendlyUser
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
    fun getUser(emailId: String): CalendlyUser? {
        return userManager.getUser(emailId)
    }

    /**
     * Save user
     *
     * @param emailId
     * @return
     */
    @Transactional
    fun saveUser(emailId: String): CalendlyUser {
        return userManager.createUser(emailId)
    }
}