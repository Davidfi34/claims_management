package com.claims_management.complaint.service;

import com.claims_management.complaint.Dto.ComplaintRequest;
import com.claims_management.complaint.Dto.ComplaintResponse;
import com.claims_management.complaint.Dto.UpdateComplaint;
import com.claims_management.complaint.Dto.mapper.ComplaintMapper;
import com.claims_management.complaint.commonFailures.Dto.mapper.CommonFailuresMapper;
import com.claims_management.complaint.commonFailures.models.CommonFailures;
import com.claims_management.complaint.commonFailures.service.CommonFailuresService;
import com.claims_management.complaint.models.Complaint;
import com.claims_management.complaint.repository.ComplaintRepository;
import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.mapper.ConsortiumMapper;
import com.claims_management.consortium.models.Consortium;
import com.claims_management.consortium.service.ConsortiumService;
import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.Dto.mapper.ServiceDataMapper;
import com.claims_management.serviceData.models.ServiceData;
import com.claims_management.serviceData.service.ServiceDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.claims_management.utils.DateTimeUtils;

import java.util.Optional;

@Service
public class ComplaintServiceImp implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    private final ComplaintMapper complaintMapper;
    private final ServiceDataService serviceDataService;
    private final ServiceDataMapper serviceDataMapper;
    private final ConsortiumService consortiumService;
    private final ConsortiumMapper consortiumMapper;
    private final CommonFailuresService commonFailuresService;
    private final CommonFailuresMapper commonFailuresMapper;

    private DateTimeUtils dateTimeUtils;

    public ComplaintServiceImp(ComplaintRepository complaintRepository,
                               ComplaintMapper complaintMapper,
                               ServiceDataService serviceDataService,
                               ServiceDataMapper serviceDataMapper,
                               ConsortiumService consortiumService,
                               ConsortiumMapper consortiumMapper,
                               CommonFailuresService commonFailuresService,
                               CommonFailuresMapper commonFailuresMapper,
                               DateTimeUtils dateTimeUtils){
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
        this.serviceDataService = serviceDataService;
        this.serviceDataMapper = serviceDataMapper;
        this.consortiumService = consortiumService;
        this.consortiumMapper = consortiumMapper;
        this.commonFailuresService = commonFailuresService;
        this.commonFailuresMapper = commonFailuresMapper;
        this.dateTimeUtils = dateTimeUtils;
    }


    @Override
    public ComplaintResponse save(ComplaintRequest complaintRequest) {
        //TODO: get Service and consortium
        ServiceResponse serviceResponse = serviceDataService.getServiceById(complaintRequest.id_service());
        ConsortiumResponse consortiumResponse = consortiumService.getConsortiumById(complaintRequest.id_consortium());

        CommonFailures commonFailures = complaintRequest.id_failures()!= null
                ? commonFailuresMapper.commonFailuresResponseToCommonFailures(
                        commonFailuresService.getById(complaintRequest.id_failures())) : null;

        //TODO: create Complaint
        Complaint complaint = new Complaint(null,
                complaintRequest.description(),dateTimeUtils.getCurrentDate(),
                complaintRequest.firstname(), complaintRequest.lastname(), complaintRequest.phone(),
                serviceDataMapper.serviceResponseToServiceData(serviceResponse),
                consortiumMapper.consortiumResponseToConsortium(consortiumResponse),commonFailures, true
        );
        return new ComplaintResponse(complaintRepository.save(complaint));
    }

    @Override
    public ComplaintResponse getById(Long id) {
        Optional<Complaint> complaintOptional = complaintRepository.findById(id);
        if (complaintOptional.isPresent()) {
            return new ComplaintResponse(complaintOptional.get());
        }
        throw new IntegrityValidation("commonFailures not found");
    }

    @Override
    public Page<ComplaintResponse> getAll(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return complaintRepository.findAll(pageable)
                .map(complaint -> new ComplaintResponse(complaint));
    }

    @Override
    public Page<ComplaintResponse> getAllActive(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return complaintRepository.findByActiveTrue(pageable)
                .map(complaint -> new ComplaintResponse(complaint));
    }

    @Override
    public ComplaintResponse update(UpdateComplaint updateComplaint) {
        Optional<Complaint> complaintOptional = complaintRepository.findById(updateComplaint.id());

        if (complaintOptional.isPresent()){
            ServiceData serviceData = serviceDataMapper
                    .serviceResponseToServiceData(serviceDataService.getServiceById(updateComplaint.id_service()));
            Consortium consortium = consortiumMapper.
                    consortiumResponseToConsortium(consortiumService.getConsortiumById(updateComplaint.id_consortium()));

            CommonFailures commonFailures = updateComplaint.id_failures() != null
                    ? commonFailuresMapper.commonFailuresResponseToCommonFailures(
                    commonFailuresService.getById(updateComplaint.id_failures())) : null;

            Complaint complaint = new Complaint(updateComplaint.id(),updateComplaint.description(),
                    complaintOptional.get().getDate(),updateComplaint.firstname(),updateComplaint.lastname(),
                    updateComplaint.phone(),serviceData,consortium,commonFailures,
                    complaintOptional.get().isActive());
            return new ComplaintResponse(complaintRepository.save(complaint));
        }

        throw new IntegrityValidation("complaint not found");
    }

    @Override
    public void delete(Long id) {
        Optional<Complaint> complaintOptional = complaintRepository.findById(id);
        if (!complaintOptional.isPresent()) throw new IntegrityValidation("Complaint not found");
        complaintRepository.deleteById(id);
    }
}