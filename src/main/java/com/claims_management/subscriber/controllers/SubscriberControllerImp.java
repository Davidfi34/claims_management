package com.claims_management.subscriber.controllers;

import com.claims_management.commons.controller.GenericRestController;
import com.claims_management.commons.dto.response.CustomResponse;
import com.claims_management.subscriber.Dto.SubscriberRequest;
import com.claims_management.subscriber.Dto.SubscriberResponse;
import com.claims_management.subscriber.Dto.UpdateSubscriber;

import com.claims_management.subscriber.service.SubscriberServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.claims_management.adm.constants.AdmConstants.REQUEST_ADM;
import static com.claims_management.commons.constants.GlobalApiConstant.*;
import static com.claims_management.subscriber.constants.SubscriberConstants.REQUEST_SUBSCRIBER;

@RestController
@RequestMapping(REQUEST_SUBSCRIBER)
public class SubscriberControllerImp extends GenericRestController implements SubscriberController {

    private final SubscriberServiceImp subscriberServiceImp;

    public SubscriberControllerImp(SubscriberServiceImp subscriberServiceImp){
        this.subscriberServiceImp = subscriberServiceImp;
    }


    @Override
    public ResponseEntity<CustomResponse> createSubscriber(SubscriberRequest subscriberRequest) {
        return ok(subscriberServiceImp.save(subscriberRequest),CREATED,REQUEST_SUBSCRIBER);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllSubscribers(int numberPage) {
        return ok(subscriberServiceImp.getAllSubscriber(numberPage),null, REQUEST_SUBSCRIBER);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllActiveSubscribers(int numberPage) {
        return ok(subscriberServiceImp.getAllActiveSubscriber(numberPage),null, REQUEST_SUBSCRIBER);
    }


    @Override
    public ResponseEntity<CustomResponse> getSubscriberById(Long id) {
        return ok(subscriberServiceImp.getSubscriberById(id),CREATED,REQUEST_SUBSCRIBER);

    }

    @Override
    public ResponseEntity<CustomResponse> updateSubscriber(UpdateSubscriber updateSubscriber) {
        SubscriberResponse subscriberResponse = subscriberServiceImp.updateSubscriber(updateSubscriber);
        return ok(subscriberResponse,null,REQUEST_SUBSCRIBER);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteSubscriberById(Long id) {
        subscriberServiceImp.deleteSubscriber(id);
        return ok(null,DELETED_SUCCESSFULLY,REQUEST_SUBSCRIBER);
    }
}
