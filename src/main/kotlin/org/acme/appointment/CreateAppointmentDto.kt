package org.acme.appointment

import java.time.LocalDateTime
import java.util.*

data class AppointmentDto(
    val patientId: Long,
    val practitionerId: UUID,
    val appointmentDate: LocalDateTime,
    val status: String = "Scheduled",
    val serviceCategory: String?,
    val serviceType: String?,
    val reasonCode: String?,
    val description: String?
)