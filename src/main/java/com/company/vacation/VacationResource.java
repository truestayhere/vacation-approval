package com.company.vacation;

import com.company.vacation.dto.VacationRequestDTO;
import com.company.vacation.model.VacationRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/vacations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VacationResource {

    @POST
    @Transactional
    public Response createVacationRequest(@Valid VacationRequestDTO requestDTO) {
        VacationRequest newVacation = new VacationRequest();
        newVacation.employeeName = requestDTO.employeeName;
        newVacation.startDate = requestDTO.startDate;
        newVacation.endDate = requestDTO.endDate;
        newVacation.status = "PENDING";

        newVacation.persist();

        return Response.status(Response.Status.CREATED).entity(newVacation).build();
    }

    @GET
    public List<VacationRequest> getAllVacations() {
        return  VacationRequest.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getVacationById(@PathParam("id") Long id) {
        return VacationRequest.findByIdOptional(id)
                .map(vacation -> Response.ok(vacation).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}/cancel")
    @Transactional
    public Response cancelVacationRequest(@PathParam("id") Long id) {
        return VacationRequest.<VacationRequest>findByIdOptional(id)
                .map(vacation -> {
                    if (!"PENDING".equals(vacation.status)) {
                        return Response.status(Response.Status.CONFLICT)
                                .build();
                    }

                    vacation.status = "CANCELLED";
                    vacation.persist();

                    return Response.ok(vacation).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}/approve")
    @Transactional
    public Response approveVacationRequest(@PathParam("id") Long id) {
        return VacationRequest.<VacationRequest>findByIdOptional(id)
                .map(vacation -> {
                    if (!"PENDING".equals(vacation.status)) {
                        return Response.status(Response.Status.CONFLICT)
                                .build();
                    }

                    vacation.status = "APPROVED";
                    vacation.persist();

                    return Response.ok(vacation).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }


}
