package org.acme.services

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.speciality.SpecialityEntity
import org.acme.speciality.SpecialityRepository

@ApplicationScoped
class ServiceManagementService {
    @Inject
    lateinit var serviceRepository: ServiceRepository

    @Inject
    lateinit var specialityRepository: SpecialityRepository

    fun listServices(): List<ServiceEntity> = serviceRepository.listAll()

    fun listSpecialities(): List<SpecialityEntity> = specialityRepository.listAll()

    @Transactional
    fun createService(service: ServiceEntity): ServiceEntity {
        serviceRepository.persist(service)
        return service
    }

    @Transactional
    fun updateService(id: Long, updated: ServiceEntity): ServiceEntity {
        val existing = serviceRepository.findById(id) ?: throw NotFoundException("Service not found")
        updated.id = existing.id
        return serviceRepository.getEntityManager().merge(updated)
    }

    @Transactional
    fun deleteService(id: Long) {
        serviceRepository.deleteById(id)
    }

    @Transactional
    fun createSpeciality(speciality: SpecialityEntity): SpecialityEntity {
        specialityRepository.persist(speciality)
        return speciality
    }

    @Transactional
    fun updateSpeciality(id: Long, updated: SpecialityEntity): SpecialityEntity {
        val existing = specialityRepository.findById(id) ?: throw NotFoundException("Speciality not found")
        updated.id = existing.id
        return specialityRepository.getEntityManager().merge(updated)
    }

    @Transactional
    fun deleteSpeciality(id: Long) {
        specialityRepository.deleteById(id)
    }
}