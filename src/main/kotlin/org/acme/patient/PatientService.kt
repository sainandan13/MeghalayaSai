package org.acme.patient

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import java.time.LocalDate

@ApplicationScoped
class PatientService {
    @Inject
    lateinit var em: EntityManager
    @Inject
    lateinit var patientRepository: PatientRepository

    @Transactional
    fun createPatient(dto: PatientDto): Patient {
        val age = LocalDate.now().year - dto.birthDate.year
        val patient = dto.toEntity(age)

        val existing = patientRepository.find(
            "abha = ?1 or email = ?2", dto.abha, dto.email
        ).list()

        if (existing.isNotEmpty()) {
            throw WebApplicationException(
                "A patient with the same ABHA or Email already exists!",
                Response.Status.CONFLICT
            )
        }

        require(patient.id == null) { "New patient ID must be null!" }
        patientRepository.persist(patient)
        return patient
    }

    fun listPatients(): List<Patient> =
        patientRepository.listAll()

    fun searchByCityOrName(city: String?, name: String?): List<Patient> {
        val queryBuilder = StringBuilder("FROM Patient p WHERE 1=1")
        val params = mutableMapOf<String, Any>()

        if (!city.isNullOrBlank()) {
            queryBuilder.append(" AND LOWER(p.city) = LOWER(:city)")
            params["city"] = city
        }

        if (!name.isNullOrBlank()) {
            queryBuilder.append(" AND (LOWER(p.firstName) LIKE LOWER(:name) OR LOWER(p.lastName) LIKE LOWER(:name))")
            params["name"] = "%$name%"
        }

        val query = em.createQuery(queryBuilder.toString(), Patient::class.java)
        params.forEach { (key, value) -> query.setParameter(key, value) }

        return query.resultList
    }

    @Transactional
    fun updatePatient(id: Long, dto: PatientDto): Patient {
        val existing = patientRepository.findById(id) ?: throw NotFoundException("Patient not found")

        // Update fields manually
        existing.firstName = dto.firstName
        existing.lastName = dto.lastName
        existing.middleName = dto.middleName
        existing.namePrefix = dto.namePrefix
        existing.nameSuffix = dto.nameSuffix
        existing.preferredName = dto.preferredName
        existing.genderIdentity = dto.genderIdentity
        existing.gender = dto.gender
        existing.preferredPronouns = dto.preferredPronouns
        existing.birthDate = dto.birthDate
        existing.age = LocalDate.now().year - dto.birthDate.year
        existing.childrenCount = dto.childrenCount?.toString()
        existing.bloodGroup = dto.bloodGroup
        existing.maritalStatus = dto.maritalStatus
        existing.abha = dto.abha
        existing.mrn = dto.mrn
        existing.aadhar = dto.aadhar
        existing.pan = dto.pan
        existing.addressLine1 = dto.addressLine1
        existing.addressLine2 = dto.addressLine2
        existing.city = dto.city
        existing.state = dto.state
        existing.postalCode = dto.postalCode
        existing.country = dto.country
        existing.landmark = dto.landmark
        existing.email = dto.email
        existing.phone = dto.phone
        existing.mobilePhone = dto.mobilePhone
        existing.emergencyName = dto.emergencyName
        existing.emergencyRelation = dto.emergencyRelation
        existing.emergencyPhone = dto.emergencyPhone
        existing.chiefComplaint = dto.chiefComplaint
        existing.consultantName = dto.consultantName
        existing.department = dto.department
        existing.dateOfVisit = dto.dateOfVisit?.toString()
        existing.timeOfVisit = dto.timeOfVisit
        existing.appointmentType = dto.appointmentType
        existing.referredBy = dto.referredBy
        existing.comments = dto.comments
        existing.preferredContactMethod = dto.preferredContactMethod
        existing.appointmentReminders = dto.appointmentReminders
        existing.interpreterRequired = dto.interpreterRequired?.toString()
        existing.ethnicity = dto.ethnicity
        existing.race = dto.race
        existing.organDonor = dto.organDonor
        existing.livingWill = dto.livingWill
        existing.powerOfAttorney = dto.powerOfAttorney

        return existing // No need to merge; the entity is already managed in a @Transactional context
    }

    @Transactional
    fun deletePatient(id: Long) {
        val patient = patientRepository.findById(id) ?: throw NotFoundException("Patient not found")
        patientRepository.delete(patient)
    }

    private fun PatientDto.toEntity(age: Int): Patient {
        val patient = Patient()
        patient.firstName = firstName
        patient.lastName = lastName
        patient.middleName = middleName
        patient.namePrefix = namePrefix
        patient.nameSuffix = nameSuffix
        patient.preferredName = preferredName
        patient.genderIdentity = genderIdentity
        patient.gender = gender
        patient.preferredPronouns = preferredPronouns
        patient.birthDate = birthDate
        patient.age = age
        patient.childrenCount = childrenCount?.toString()
        patient.bloodGroup = bloodGroup
        patient.maritalStatus = maritalStatus
        patient.abha = abha
        patient.mrn = mrn
        patient.aadhar = aadhar
        patient.pan = pan
        patient.addressLine1 = addressLine1
        patient.addressLine2 = addressLine2
        patient.city = city
        patient.state = state
        patient.postalCode = postalCode
        patient.country = country
        patient.landmark = landmark
        patient.email = email
        patient.phone = phone
        patient.mobilePhone = mobilePhone
        patient.emergencyName = emergencyName
        patient.emergencyRelation = emergencyRelation
        patient.emergencyPhone = emergencyPhone
        patient.chiefComplaint = chiefComplaint
        patient.consultantName = consultantName
        patient.department = department
        patient.dateOfVisit = dateOfVisit?.toString()
        patient.timeOfVisit = timeOfVisit
        patient.appointmentType = appointmentType
        patient.referredBy = referredBy
        patient.comments = comments
        patient.preferredContactMethod = preferredContactMethod
        patient.appointmentReminders = appointmentReminders
        patient.interpreterRequired = interpreterRequired?.toString()
        patient.ethnicity = ethnicity
        patient.race = race
        patient.organDonor = organDonor
        patient.livingWill = livingWill
        patient.powerOfAttorney = powerOfAttorney
        return patient
    }
}
