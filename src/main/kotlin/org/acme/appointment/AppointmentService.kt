package org.acme.appointment

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.patient.Patient
import org.acme.services.ServiceEntity


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

        val service = dto.serviceId?.let { entityManager.find(ServiceEntity::class.java, it) }

        val appointment = AppointmentEntity()
        appointment.patient = patient

        appointment.appointmentDate = dto.appointmentDate

        appointment.notes = dto.notes
        appointment.startTime = dto.startTime
        appointment.endTime = dto.endTime
        appointment.provider = dto.provider
        appointment.location = dto.location
        appointment.speciality = dto.speciality
        appointment.service = service

        appointmentRepository.persist(appointment)
        return appointment
    }

    fun listAppointments(): List<AppointmentEntity> = appointmentRepository.listAll()

    fun findByPatientId(patientId: Long): List<AppointmentEntity> =
        appointmentRepository.find("patient.id", patientId).list()
}

