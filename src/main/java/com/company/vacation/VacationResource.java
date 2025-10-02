package com.company.vacation;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/vacations")
public class VacationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listVacations()
    {
        return "[]";
    }
}
