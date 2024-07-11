package com.calendly.model

import javax.persistence.*

/**
 * User
 *
 * @property id
 * @property emailId
 * @constructor Create empty User
 */
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int? = 0,

    @Column(nullable = false, unique = true)
    val emailId: String = ""
)