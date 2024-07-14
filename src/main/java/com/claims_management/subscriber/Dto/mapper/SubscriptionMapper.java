package com.claims_management.subscriber.Dto.mapper;

import com.claims_management.consortium.Dto.mapper.ConsortiumMapper;
import com.claims_management.consortium.models.Consortium;
import com.claims_management.consortium.service.ConsortiumService;
import com.claims_management.serviceData.Dto.mapper.ServiceDataMapper;
import com.claims_management.serviceData.models.ServiceData;
import com.claims_management.serviceData.service.ServiceDataService;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import com.claims_management.subscriber.models.Subscriber;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    private final ConsortiumService consortiumService;
    private final ServiceDataService serviceDataService;
    private final ConsortiumMapper consortiumMapper;
    private final ServiceDataMapper serviceDataMapper;

    public SubscriptionMapper(ConsortiumService consortiumService,
                              ServiceDataService serviceDataService,
                              ConsortiumMapper consortiumMapper,
                              ServiceDataMapper serviceDataMapper
                              ){
        this.consortiumService = consortiumService;
        this.serviceDataService = serviceDataService;
        this.consortiumMapper = consortiumMapper;
        this.serviceDataMapper = serviceDataMapper;

    }



    public Subscriber updateSubscriberToSubscriber(UpdateSubscriber updateSubscriber){
        Subscriber subscriber = new Subscriber();

        Consortium consortium = consortiumMapper.consortiumResponseToConsortium(
                consortiumService.getConsortiumById(updateSubscriber.idConsortium()));

        ServiceData serviceData = serviceDataMapper.
                serviceResponseToServiceData(serviceDataService.getServiceById(updateSubscriber.idService()));

        subscriber.setId(updateSubscriber.id());
        subscriber.setConsortium(consortium);
        subscriber.setService(serviceData);
        subscriber.setStartDate(updateSubscriber.startDate());
        subscriber.setEndDate(updateSubscriber.endDate());
        return subscriber;
    }
}
