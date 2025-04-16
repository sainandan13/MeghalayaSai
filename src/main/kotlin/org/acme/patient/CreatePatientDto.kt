package org.acme.patient

import java.time.LocalDate

import jakarta.validation.constraints.*
import org.acme.validation.VerifyAbha

data class PatientDto(
    @field:NotBlank val firstName: String,
    @field:NotBlank val lastName: String,
    val middleName: String?,
    val namePrefix: String?,
    val nameSuffix: String?,
    val preferredName: String?,
    val genderIdentity: String?,
    val gender: String?,
    val preferredPronouns: String?,
    @field:NotNull val birthDate: LocalDate,
    val childrenCount: Int?,
    val bloodGroup: String?,
    val maritalStatus: String?,

    @field:VerifyAbha
    val abha: String?,
    @field:NotBlank val mrn: String,
    val aadhar: String?,
    val pan: String?,
    @field:NotBlank val addressLine1: String,
    val addressLine2: String?,
    @field:NotBlank val city: String,
    @field:NotBlank val state: String,
    @field:NotBlank val postalCode: String,
    val country: String = "India",
    val landmark: String?,
    @field:Email @field:NotBlank val email: String,
    val phone: String?,
    @field:NotBlank val mobilePhone: String,
    val emergencyName: String?,
    val emergencyRelation: String?,
    val emergencyPhone: String?,
    val chiefComplaint: String?,
    val consultantName: String?,
    val department: String?,
    val dateOfVisit: LocalDate?,
    val timeOfVisit: String?,
    val appointmentType: String?,
    val referredBy: String?,
    val comments: String?,
    val preferredContactMethod: String?,
    val appointmentReminders: Boolean,
    val interpreterRequired: Boolean,
    val ethnicity: String?,
    val race: String?,
    val organDonor: Boolean,
    val livingWill: Boolean,
    val powerOfAttorney: Boolean
)