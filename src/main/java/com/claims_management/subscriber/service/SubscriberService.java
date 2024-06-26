package com.claims_management.subscriber.service;

import com.claims_management.subscriber.Dto.SubscriberRequest;
import com.claims_management.subscriber.Dto.SubscriberResponse;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import org.springframework.data.domain.Page;


public interface SubscriberService {

    public SubscriberResponse save(SubscriberRequest subscriberRequest);
    public SubscriberResponse getSubscriberById(Long id);
    public Page<SubscriberResponse> getAllSubscriber(int numberPage);
    public SubscriberResponse updateSubscriber(UpdateSubscriber updateSubscriber);
    public void deleteSubscriber(Long id);
}
