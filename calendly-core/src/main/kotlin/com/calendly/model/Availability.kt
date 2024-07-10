package com.calendly.model

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "availabilities")
data class Availability(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val emailId: String,

    @Column(nullable = false)
    val startDateTime: LocalDateTime,

    @Column(nullable = false)
    val endDateTime: LocalDateTime
)