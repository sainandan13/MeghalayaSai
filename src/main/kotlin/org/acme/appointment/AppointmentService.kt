package org.acme.appointment

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.patient.Patient

@ApplicationScoped
class AppointmentService {

    @Inject
    lateinit var appointmentRepository: AppointmentRepository

    @Inject
    lateinit var entityManager: EntityManager

    @Transactional
    fun createAppointment(dto: AppointmentDto): AppointmentEntity {
        val patient = entityManager.find(Patient::class.java, dto.patientId)
            ?: throw NotFoundException("Patient not found")

        val appointment = AppointmentEntity()
        appointment.patient = patient
        appointment.practitionerId = dto.practitionerId
        appointment.appointmentDate = dto.appointmentDate
        appointment.status = dto.status
        appointment.serviceCategory = dto.serviceCategory
        appointment.serviceType = dto.serviceType
        appointment.reasonCode = dto.reasonCode
        appointment.description = dto.description

        appointmentRepository.persist(appointment)
        return appointment


    }

    fun listAppointments(): List<AppointmentEntity> = appointmentRepository.listAll()

    fun findByPatientId(patientId: Long): List<AppointmentEntity> =
        appointmentRepository.find("patient.id", patientId).list()
}