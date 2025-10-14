package com.company.vacation.service;


import com.company.vacation.dto.VacationRequestDTO;
import com.company.vacation.model.VacationRequest;
import com.company.vacation.model.VacationStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class VacationService {

    public List<VacationRequest> listAll() {
        return VacationRequest.listAll();
    }

    public Optional<VacationRequest> findByUuid(UUID uuid) {
        return VacationRequest.find("uuid", uuid).firstResultOptional();
    }

    @Transactional
    public VacationRequest create(VacationRequestDTO requestDTO) {
        VacationRequest newVacation = new VacationRequest();

        newVacation.uuid = null;
        newVacation.employeeName = requestDTO.employeeName;
        newVacation.startDate = requestDTO.startDate;
        newVacation.endDate = requestDTO.endDate;
        newVacation.status = VacationStatus.PENDING;

        newVacation.persist();
        return newVacation;
    }

    @Transactional
    public VacationRequest cancel(UUID uuid) {
        VacationRequest vacation = findAndCheckStatus(uuid);
        vacation.status = VacationStatus.CANCELLED;
        vacation.persist();
        return vacation;
    }

    @Transactional
    public VacationRequest approve(UUID uuid) {
        VacationRequest vacation = findAndCheckStatus(uuid);
        vacation.status = VacationStatus.APPROVED;
        vacation.persist();
        return vacation;
    }

    private VacationRequest findAndCheckStatus(UUID uuid) {
        VacationRequest vacation = findByUuid(uuid)
                .orElseThrow(() -> new WebApplicationException("Vacation uuid: " + uuid + " not found.", Response.Status.NOT_FOUND));

        if (vacation.status != VacationStatus.PENDING) {
            throw new WebApplicationException("Vacation request not with PENDING status.", Response.Status.CONFLICT);
        }

        return vacation;
    }


}
