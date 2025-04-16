package org.acme.appointment

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import org.acme.patient.Patient
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "appointments")
class AppointmentEntity : PanacheEntityBase {
    @Id
    @GeneratedValue
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    lateinit var patient: Patient

    @Column(name = "practitioner_id", nullable = false)
    lateinit var practitionerId: UUID

    @Column(name = "appointment_date", nullable = false)
    lateinit var appointmentDate: LocalDateTime

    @Column(nullable = false)
    var status: String = "Scheduled"

    var serviceCategory: String? = null
    var serviceType: String? = null
    var reasonCode: String? = null
    var description: String? = null

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}