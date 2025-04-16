package org.acme.services

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ServiceRepository : PanacheRepositoryBase<ServiceEntity, Long> {
}