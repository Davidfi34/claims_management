package com.claims_management.subscriber.validator.impl;

import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import com.claims_management.subscriber.validator.SubscriberValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriberValidatorImpl implements SubscriberValidator {

    //TODO: VALID endDate
    @Override
    public void ValidateEditSubscriptionDate(UpdateSubscriber data) {
        LocalDate startDate = data.startDate();
        LocalDate endDate   = data.endDate();

        boolean endDateValid = endDate != null ? endDate.isBefore(startDate) : false;

        //TODO: startDate <= endDate
        if (endDateValid) throw new IntegrityValidation("the end date of the subscription is incorrect");
    }
}