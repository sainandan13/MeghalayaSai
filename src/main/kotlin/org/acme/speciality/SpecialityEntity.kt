package org.acme.speciality

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

@Entity
@Table(name = "specialities")
class SpecialityEntity : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    var description: String? = null
}
