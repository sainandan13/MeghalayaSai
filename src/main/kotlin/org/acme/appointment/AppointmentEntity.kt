package org.acme.appointment

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import org.acme.patient.Patient
import org.acme.services.ServiceEntity
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

    @Column(name = "appointment_date", nullable = false)
    lateinit var appointmentDate: LocalDateTime





    var startTime: LocalDateTime? = null
    var endTime: LocalDateTime? = null
    var notes: String? = null


    var provider: String? = null
    var location: String? = null
    var speciality: String? = null

    @ManyToOne
    @JoinColumn(name = "service_id")
    var service: ServiceEntity? = null

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}