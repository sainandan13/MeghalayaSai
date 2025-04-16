package org.acme.patient

import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import java.time.LocalDate


@Path("/patients")
@Tag(name = "Patient", description = "Manage patient records")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PatientController {

    @Inject
    lateinit var service: PatientService

    @POST
    @Operation(summary = "Create a new patient")
    fun create(@Valid dto: PatientDto): Response {
        val patient = service.createPatient(dto)
        return Response.status(Response.Status.CREATED).entity(patient).build()
    }

    @GET
    @Operation(summary = "List or search patients")
    fun getAll(@QueryParam("city") city: String?, @QueryParam("name") name: String?): List<Patient> =
        service.searchByCityOrName(city, name)

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a patient by ID")
    fun update(@PathParam("id") id: Long, @Valid dto: PatientDto): Response {
        val patient = service.updatePatient(id, dto)
        return Response.ok(patient).build()
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a patient by ID")
    fun delete(@PathParam("id") id: Long): Response {
        service.deletePatient(id)
        return Response.noContent().build()
    }

    @GET
    @Path("/search")
    @Operation(summary = "Search patients by email, place, ABHA ID or id")
    fun searchPatients(
        @QueryParam("id") id: String?,
        @QueryParam("firstName") firstName: String?,
        @QueryParam("lastName") lastName: String?,
        @QueryParam("mobile") mobile: String?,
        @QueryParam("email") email: String?,
        @QueryParam("dobFrom") dobFrom: LocalDate?,
        @QueryParam("dobTo") dobTo: LocalDate?
    ): Response {
        val results = service.search(
            id = id,
            firstName = firstName,
            lastName = lastName,
            mobile = mobile,
            email = email,
            dobFrom = dobFrom,
            dobTo = dobTo
        )
        return Response.ok(results).build()
    }
}
