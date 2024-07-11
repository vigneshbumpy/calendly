package com.calendly.dal


import com.calendly.dao.UserDao
import com.calendly.model.User
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
    fun findByEmailId(emailId: String): User? =
        userDao.findByEmailId(emailId)

    /**
     * Save
     *
     * @param user
     * @return
     */
    fun save(user: User): User = userDao.save(user)

}