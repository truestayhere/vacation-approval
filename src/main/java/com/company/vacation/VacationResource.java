package com.company.vacation;

import com.company.vacation.dto.VacationRequestDTO;
import com.company.vacation.model.VacationRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

        newVacation.persist();

        return Response.status(Response.Status.CREATED).entity(newVacation).build();
    }
}
