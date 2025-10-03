package com.company.vacation;

import com.company.vacation.dto.VacationRequestDTO;
import com.company.vacation.model.VacationRequest;
import com.company.vacation.service.VacationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/vacations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VacationResource {

    @Inject
    VacationService service;

    @POST
    public Response createVacationRequest(@Valid VacationRequestDTO requestDTO) {
        VacationRequest newVacation = service.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(newVacation).build();
    }

    @GET
    public List<VacationRequest> getAllVacations() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getVacationById(@PathParam("id") UUID uuid) {
        return service.findByUuid(uuid)
                .map(vacation -> Response.ok(vacation).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}/cancel")
    public Response cancelVacationRequest(@PathParam("id") UUID uuid) {
        try {
            return Response.ok(service.cancel(uuid)).build();
        } catch (WebApplicationException e) {
            return e.getResponse();
        }
    }

    @PUT
    @Path("/{id}/approve")
    public Response approveVacationRequest(@PathParam("id") UUID uuid) {
        try {
            return Response.ok(service.approve(uuid)).build();
        } catch (WebApplicationException e) {
            return e.getResponse();
        }
    }

}
