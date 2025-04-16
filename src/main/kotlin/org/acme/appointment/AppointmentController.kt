package org.acme.appointment

import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.*
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/appointments")
@Tag(name = "Appointments", description = "Operations related to appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AppointmentController {

    @Inject
    lateinit var appointmentService: AppointmentService

    @POST
    @Operation(summary = "Create a new appointment")
    fun create(@Valid dto: AppointmentDto): Response {
        val appointment = appointmentService.createAppointment(dto)
        return Response.status(Response.Status.CREATED)
            .entity(mapOf("message" to "Appointment created", "id" to appointment.id))
            .build()
    }

    @GET
    @Operation(summary = "List all appointments")
    fun list(): List<AppointmentEntity> = appointmentService.listAppointments()

    @GET
    @Path("/patient/{patientId}")
    @Operation(summary = "Get appointments by patient ID")
    fun getByPatient(@PathParam("patientId") patientId: Long): List<AppointmentEntity> =
        appointmentService.findByPatientId(patientId)
}
