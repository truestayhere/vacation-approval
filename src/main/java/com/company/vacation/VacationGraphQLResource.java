package com.company.vacation;


import com.company.vacation.model.VacationRequest;
import com.company.vacation.model.VacationStatus;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;

import java.util.List;
import java.util.UUID;

@GraphQLApi
public class VacationGraphQLResource {

    @Query("allVacations")
    @Description("Get all vacation requests")
    public List<VacationRequest> getAllVacations() {
        return VacationRequest.listAll();
    }

    @Query
    @Description("Get a vacation request by UUID")
    public VacationRequest vacationById(@Name("id")UUID uuid) {
        return VacationRequest.findById(uuid);
    }

    @Mutation
    @Transactional
    @Description("Create a new vacation request")
    public VacationRequest createVacation(VacationRequest newVacation) {
        newVacation.status = VacationStatus.PENDING;

        if (newVacation.isPersistent()) {
            VacationRequest.getEntityManager().merge(newVacation);
        } else {
            newVacation.persist();
        }

        return newVacation;
    }

    @Mutation
    @Transactional
    @Description("Approve a vacation request")
    public VacationRequest approveVacation(@Name("id") UUID uuid) {
        VacationRequest request = VacationRequest.findById(uuid);
        if (request != null && request.status == VacationStatus.PENDING) {
            request.status = VacationStatus.APPROVED;
            request.persist();
        }
        return request;
    }

    @Mutation
    @Transactional
    @Description("Cancel a vacation request")
    public VacationRequest cancelVacation(@Name("id") UUID uuid) {
        VacationRequest request = VacationRequest.findById(uuid);
        if (request != null && request.status == VacationStatus.PENDING) {
            request.status = VacationStatus.CANCELLED;
            request.persist();
        }
        return request;
    }
}
