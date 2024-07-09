package com.calendly.model

import javax.persistence.*

@Entity
@Table(name = "availability_slots")
data class AvailabilitySlot(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val startTime: java.time.Instant
)