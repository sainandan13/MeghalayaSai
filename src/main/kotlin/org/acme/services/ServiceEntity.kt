package org.acme.services


import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import org.acme.speciality.SpecialityEntity
import java.time.LocalDateTime

@Entity
@Table(name = "services")
class ServiceEntity : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    lateinit var serviceName: String

    var description: String? = null
    var duration: Int? = null // duration in minutes
    var startTime: LocalDateTime? = null
    var endTime: LocalDateTime? = null
    var maxLoad: Int? = null
    var location: String? = null

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    var speciality: SpecialityEntity? = null
}