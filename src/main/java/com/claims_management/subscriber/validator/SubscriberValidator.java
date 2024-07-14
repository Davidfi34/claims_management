package com.claims_management.subscriber.validator;

import com.claims_management.subscriber.Dto.UpdateSubscriber;

public interface SubscriberValidator {

    public void ValidateEditSubscriptionDate(UpdateSubscriber data);
}
