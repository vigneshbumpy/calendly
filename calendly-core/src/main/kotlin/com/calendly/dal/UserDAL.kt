package com.calendly.dal


import com.calendly.dao.UserDao
import com.calendly.model.CalendlyUser
import org.springframework.stereotype.Component

/**
 * User DAL
 *
 * @property userDao
 * @constructor Create empty User DAL
 */
@Component
class UserDAL(
    private val userDao: UserDao
) {
    /**
     * Find by email id
     *
     * @param emailId
     * @return
     */
    fun findByEmailId(emailId: String): CalendlyUser? =
        userDao.findByEmailId(emailId)

    /**
     * Save
     *
     * @param calendlyUser
     * @return
     */
    fun save(calendlyUser: CalendlyUser): CalendlyUser = userDao.save(calendlyUser)

}