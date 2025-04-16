package org.acme.speciality

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class SpecialityRepository: PanacheRepositoryBase<SpecialityEntity, Long> {
}