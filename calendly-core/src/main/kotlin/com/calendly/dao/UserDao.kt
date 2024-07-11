package com.calendly.dao

import com.calendly.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * User dao
 *
 * @constructor Create empty User dao
 */
@Repository
interface UserDao : JpaRepository<User, Long> {
    /**
     * Find by email id
     *
     * @param emailId
     * @return
     */
    fun findByEmailId(emailId: String): User?

}