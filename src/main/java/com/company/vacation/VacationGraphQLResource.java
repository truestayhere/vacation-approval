package com.company.vacation;


import com.company.vacation.dto.VacationRequestDTO;
import com.company.vacation.model.VacationRequest;
import com.company.vacation.service.VacationService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;

import java.util.List;
import java.util.UUID;

@GraphQLApi
public class VacationGraphQLResource {

    @Inject
    VacationService vacationService;

    @Query("allVacations")
    @Description("Get all vacation requests")
    public List<VacationRequest> getAllVacations() {
        return vacationService.listAll();
    }

    @Query
    @Description("Get a vacation request by UUID")
    public VacationRequest vacationById(@Name("id")UUID uuid) {
        return vacationService.findByUuid(uuid).orElse(null);
    }

    @Mutation
    @Transactional
    @Description("Create a new vacation request")
    public VacationRequest createVacation(VacationRequestDTO requestDTO) {
        return vacationService.create(requestDTO);
    }

    @Mutation
    @Transactional
    @Description("Approve a vacation request")
    public VacationRequest approveVacation(@Name("id") UUID uuid) {
        return vacationService.approve(uuid);
    }

    @Mutation
    @Transactional
    @Description("Cancel a vacation request")
    public VacationRequest cancelVacation(@Name("id") UUID uuid) {
        return vacationService.cancel(uuid);
    }
}
