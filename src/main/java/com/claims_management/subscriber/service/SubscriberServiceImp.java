package com.claims_management.subscriber.service;

import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.models.Consortium;
import com.claims_management.consortium.service.ConsortiumService;
import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.service.ServiceDataService;
import com.claims_management.subscriber.Dto.SubscriberRequest;
import com.claims_management.subscriber.Dto.SubscriberResponse;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import com.claims_management.subscriber.repository.SubscriberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.claims_management.serviceData.models.ServiceData;
import org.springframework.stereotype.Service;
import com.claims_management.subscriber.models.Subscriber;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class SubscriberServiceImp implements SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final ServiceDataService serviceDataService;
    private final ConsortiumService consortiumService;

    public SubscriberServiceImp(SubscriberRepository subscriberRepository,
                                ServiceDataService serviceDataService,
                                ConsortiumService consortiumService){
        this.subscriberRepository = subscriberRepository;
        this.serviceDataService = serviceDataService;
        this.consortiumService = consortiumService;
    }

    @Override
    public SubscriberResponse save(SubscriberRequest subscriberRequest) {

        ServiceResponse serviceResponse = serviceDataService.getServiceById(subscriberRequest.idService());
        ConsortiumResponse consortiumResponse = consortiumService.getConsortiumById(subscriberRequest.idConsortium());

        if (serviceResponse == null || consortiumResponse == null) {
            throw new IllegalArgumentException("Service or Consortium not found");
        }
        Subscriber subscriber =
                    new Subscriber(null, new Consortium(
                            consortiumResponse.id(),consortiumResponse.address(),
                            consortiumResponse.adm()),
                            new ServiceData(serviceResponse.id(),serviceResponse.name(),
                                    serviceResponse.description()), LocalDate.now(),null);
        return new SubscriberResponse(subscriberRepository.save(subscriber));
    }

    @Override
    public SubscriberResponse getSubscriberById(Long id) {
        Optional<Subscriber> subscriberOptional = subscriberRepository.findById(id);
        if (subscriberOptional.isPresent()) return new SubscriberResponse(subscriberOptional.get());
        throw new IllegalArgumentException("Subscriber not found");
    }

    @Override
    public Page<SubscriberResponse> getAllSubscriber(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return subscriberRepository.findAll(pageable).map(SubscriberResponse::new);
    }

    @Override
    public SubscriberResponse updateSubscriber(UpdateSubscriber updateSubscriber) {
        LocalDate endDate = null;

        ServiceResponse serviceResponse = serviceDataService.getServiceById(updateSubscriber.idService());
        ConsortiumResponse consortiumResponse = consortiumService.getConsortiumById(updateSubscriber.idConsortium());

        if (serviceResponse == null || consortiumResponse == null) {
            throw new IntegrityValidation("Service or Consortium not found");
        }

        Subscriber subscriberData = subscriberRepository.findById(updateSubscriber.id())
                .orElseThrow(() -> new IntegrityValidation("Subscriber not found"));

        if (updateSubscriber.endDate() != null) {
            endDate = updateSubscriber.endDate();
        }

        Subscriber subscriber = new Subscriber(updateSubscriber.id(),
                new Consortium(consortiumResponse.id(), consortiumResponse.address(), consortiumResponse.adm()),
                new ServiceData(serviceResponse.id(), serviceResponse.name(), serviceResponse.description()),
                subscriberData.getStartDate(), endDate);

        return new SubscriberResponse(subscriberRepository.save(subscriber));
    }

    @Override
    public void deleteSubscriber(Long id) {
        Optional<Subscriber> subscriberOptional = subscriberRepository.findById(id);
        if (!subscriberOptional.isPresent()) throw new IntegrityValidation("Subscriber not found");
        subscriberRepository.deleteById(id);
    }
}