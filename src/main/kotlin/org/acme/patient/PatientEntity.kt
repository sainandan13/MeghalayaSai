package org.acme.patient

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "patients")
class Patient : PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // Personal Information
    lateinit var firstName: String
    lateinit var lastName: String
    var middleName: String? = null
    var namePrefix: String? = null
    var nameSuffix: String? = null
    var preferredName: String? = null
    var genderIdentity: String? = null
    var gender: String? = null
    var preferredPronouns: String? = null
    lateinit var birthDate: LocalDate
    var age: Int? = null
    var childrenCount: String? = null
    var bloodGroup: String? = null
    var maritalStatus: String? = null

    // Identifiers
    var abha: String? = null
    lateinit var mrn: String
    var aadhar: String? = null
    var pan: String? = null

    // Address Details
    lateinit var addressLine1: String
    var addressLine2: String? = null
    lateinit var city: String
    lateinit var state: String
    lateinit var postalCode: String
    var country: String = "India"
    var landmark: String? = null

    // Contact Information
    lateinit var email: String
    var phone: String? = null
    lateinit var mobilePhone: String

    // Emergency Contact
    var emergencyName: String? = null
    var emergencyRelation: String? = null
    var emergencyPhone: String? = null

    // Other Medical Details
    var chiefComplaint: String? = null
    var consultantName: String? = null
    var department: String? = null
    var dateOfVisit: String? = null
    var timeOfVisit: String? = null
    var appointmentType: String? = null
    var referredBy: String? = null
    var comments: String? = null

    // Preferences & Demographics
    var preferredContactMethod: String? = null
    var appointmentReminders: Boolean = false
    var interpreterRequired: String? = false.toString()
    var ethnicity: String? = null
    var race: String? = null
    var organDonor: Boolean = false
    var livingWill: Boolean = false
    var powerOfAttorney: Boolean = false
}
