package com.calendly.model

import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Availability(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int? = 0,

    @Column(nullable = false)
    val emailId: String = "",

    @Column(nullable = false)
    val startDateTime: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val endDateTime: LocalDateTime = LocalDateTime.now()
)