package com.calendly.manager

import com.calendly.dal.UserDAL
import com.calendly.model.CalendlyUser
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
    fun createUser(emailId: String): CalendlyUser {
        return userDAL.save(CalendlyUser(emailId = emailId))
    }

    /**
     * Get user
     *
     * @param emailId
     * @return
     */
    fun getUser(emailId: String): CalendlyUser? {
        return userDAL.findByEmailId(emailId)
    }
}