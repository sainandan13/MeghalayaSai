package org.acme.patient

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class PatientRepository : PanacheRepositoryBase<Patient, Long>{

}