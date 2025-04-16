package org.acme.services

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.*
import org.acme.speciality.SpecialityEntity
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/services")
@Tag(name = "Services", description = "Operations related to medical services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ServiceController {

    @Inject
    lateinit var serviceManagementService: ServiceManagementService

    @POST
    @Path("/create")
    fun createService(service: ServiceEntity): Response {
        val created = serviceManagementService.createService(service)
        return Response.status(Response.Status.CREATED).entity(created).build()
    }

    @PUT
    @Path("/update/{id}")
    fun updateService(@PathParam("id") id: Long, service: ServiceEntity): Response {
        val updated = serviceManagementService.updateService(id, service)
        return Response.ok(updated).build()
    }

    @DELETE
    @Path("/delete/{id}")
    fun deleteService(@PathParam("id") id: Long): Response {
        serviceManagementService.deleteService(id)
        return Response.noContent().build()
    }

    @POST
    @Path("/specialities/create")
    fun createSpeciality(speciality: SpecialityEntity): Response {
        val created = serviceManagementService.createSpeciality(speciality)
        return Response.status(Response.Status.CREATED).entity(created).build()
    }

    @PUT
    @Path("/specialities/update/{id}")
    fun updateSpeciality(@PathParam("id") id: Long, speciality: SpecialityEntity): Response {
        val updated = serviceManagementService.updateSpeciality(id, speciality)
        return Response.ok(updated).build()
    }

    @DELETE
    @Path("/specialities/delete/{id}")
    fun deleteSpeciality(@PathParam("id") id: Long): Response {
        serviceManagementService.deleteSpeciality(id)
        return Response.noContent().build()
    }



    @GET
    fun listServices(): List<ServiceEntity> = serviceManagementService.listServices()

    @GET
    @Path("/specialities")
    fun listSpecialities(): List<SpecialityEntity> = serviceManagementService.listSpecialities()
}
