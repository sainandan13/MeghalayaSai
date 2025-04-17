package org.acme.patient

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "patients_merge_logs")
class PatientMergeLog : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var duplicateCandidate1: Long = 0
    var duplicateCandidate2: Long = 0
    var duplicateScore: Double = 0.0

    @Column(columnDefinition = "TEXT")
    lateinit var candidateRecordA: String

    @Column(columnDefinition = "TEXT")
    lateinit var candidateRecordB: String

    lateinit var mergeOption: String
    var confirmMerge: Boolean = false

    var mergedIntoPatientId: Long = 0
    var discardedPatientId: Long = 0

    var mergeReason: String? = null
    lateinit var mergedBy: UUID
    var mergedOn: LocalDateTime = LocalDateTime.now()
}

// --- Merge Request DTO ---
data class PatientMergeRequest(
    val duplicateCandidate1: Long,
    val duplicateCandidate2: Long,
    val duplicateScore: Double,
    val candidateRecordA: String,
    val candidateRecordB: String,
    val mergeOption: String,
    val confirmMerge: Boolean,
    val mergedIntoPatientId: Long,
    val discardedPatientId: Long,
    val mergeReason: String?,
    val mergedBy: UUID
)
