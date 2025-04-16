package org.acme.appointment

import java.time.LocalDateTime
import java.util.*

data class AppointmentDto(
    val patientId: Long,

    val appointmentDate: LocalDateTime,





    val provider: String?,
    val location: String?,
    val speciality: String?,
    val startTime: LocalDateTime?,
    val endTime: LocalDateTime?,
    val notes: String?,
    val serviceId: Long?
)