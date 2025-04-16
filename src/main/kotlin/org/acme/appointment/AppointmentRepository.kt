package org.acme.appointment

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID


@ApplicationScoped
class AppointmentRepository: PanacheRepositoryBase<AppointmentEntity, UUID>