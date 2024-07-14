package com.calendly.model

import jakarta.persistence.*

/**
 * User
 *
 * @property id
 * @property emailId
 * @constructor Create empty User
 */
@Entity
data class CalendlyUser(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int? = 0,

    @Column(nullable = false, unique = true)
    val emailId: String = ""
)