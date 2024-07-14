package com.calendly.dao

import com.calendly.model.CalendlyUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * User dao
 *
 * @constructor Create empty User dao
 */
@Repository
interface UserDao : JpaRepository<CalendlyUser, Long> {
    /**
     * Find by email id
     *
     * @param emailId
     * @return
     */
    fun findByEmailId(emailId: String): CalendlyUser?

}