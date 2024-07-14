package com.claims_management.subscriber.service;

import com.claims_management.consortium.Dto.ConsortiumResponse;
import com.claims_management.consortium.Dto.mapper.ConsortiumMapper;
import com.claims_management.consortium.service.ConsortiumService;
import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.serviceData.Dto.ServiceResponse;
import com.claims_management.serviceData.Dto.mapper.ServiceDataMapper;
import com.claims_management.serviceData.service.ServiceDataService;
import com.claims_management.subscriber.Dto.SubscriberRequest;
import com.claims_management.subscriber.Dto.SubscriberResponse;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import com.claims_management.subscriber.Dto.mapper.SubscriptionMapper;
import com.claims_management.subscriber.repository.SubscriberRepository;
import com.claims_management.subscriber.validator.SubscriberValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.claims_management.subscriber.models.Subscriber;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class SubscriberServiceImp implements SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final ServiceDataService serviceDataService;
    private final ConsortiumService consortiumService;
    private final ConsortiumMapper consortiumMapper;
    private final ServiceDataMapper serviceDataMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriberValidator validators;

    public SubscriberServiceImp(SubscriberRepository subscriberRepository,
                                ServiceDataService serviceDataService,
                                ConsortiumService consortiumService,
                                ConsortiumMapper consortiumMapper,
                                ServiceDataMapper serviceDataMapper,
                                SubscriptionMapper subscriptionMapper,
                                SubscriberValidator validators
                                ){
        this.subscriberRepository = subscriberRepository;
        this.serviceDataService = serviceDataService;
        this.consortiumService = consortiumService;
        this.consortiumMapper = consortiumMapper;
        this.serviceDataMapper = serviceDataMapper;
        this.subscriptionMapper = subscriptionMapper;
        this.validators = validators;
    }

    @Override
    public SubscriberResponse save(SubscriberRequest subscriberRequest) {

        //TODO: get Service and consortium
        ServiceResponse serviceResponse = serviceDataService.getServiceById(subscriberRequest.idService());
        ConsortiumResponse consortiumResponse = consortiumService.getConsortiumById(subscriberRequest.idConsortium());

        //TODO: create subscriber
        Subscriber subscriber =
                    new Subscriber(null,
                            consortiumMapper.consortiumResponseToConsortium(consortiumResponse),
                            serviceDataMapper.serviceResponseToServiceData(serviceResponse),
                            LocalDate.now(),null);
        return new SubscriberResponse(subscriberRepository.save(subscriber));
    }

    @Override
    public SubscriberResponse getSubscriberById(Long id) {
        Optional<Subscriber> subscriberOptional = subscriberRepository.findById(id);
        if (subscriberOptional.isPresent()) {
            return new SubscriberResponse(subscriberOptional.get());
        }
        throw new IllegalArgumentException("Subscriber not found");
    }

    @Override
    public Page<SubscriberResponse> getAllSubscriber(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return subscriberRepository.findAll(pageable).map(SubscriberResponse::new);
    }

    @Override
    public Page<SubscriberResponse> getAllActiveSubscriber(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return subscriberRepository.findByEndDateNull(pageable).map(SubscriberResponse::new);
    }

    @Override
    public SubscriberResponse updateSubscriber(UpdateSubscriber updateSubscriber) {

        //TODO: validate Subscription date
        validators.ValidateEditSubscriptionDate(updateSubscriber);

        LocalDate endDate = null;

        //TODO: get service and consortium
        ServiceResponse serviceResponse = serviceDataService.getServiceById(updateSubscriber.idService());
        ConsortiumResponse consortiumResponse = consortiumService.getConsortiumById(updateSubscriber.idConsortium());

        Subscriber subscriberData = subscriberRepository.findById(updateSubscriber.id())
                .orElseThrow(() -> new IntegrityValidation("Subscriber not found"));

        if (updateSubscriber.endDate() != null) {
            endDate = updateSubscriber.endDate();
        }
        //TODO: create Subscriber
        Subscriber subscriber = new Subscriber(updateSubscriber.id(),
                consortiumMapper.consortiumResponseToConsortium(consortiumResponse),
                serviceDataMapper.serviceResponseToServiceData(serviceResponse),
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