package com.claims_management.subscriber.controllers;

import com.claims_management.commons.dto.response.CustomResponse;

import com.claims_management.subscriber.Dto.SubscriberRequest;
import com.claims_management.subscriber.Dto.UpdateSubscriber;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.claims_management.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.claims_management.commons.constants.GlobalApiConstant.ID_PARAM;


public interface SubscriberController {


    @PostMapping
    ResponseEntity<CustomResponse> createSubscriber(@Valid @RequestBody SubscriberRequest subscriberRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllSubscribers(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getSubscriberById(@Valid @PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateSubscriber(@Valid @RequestBody UpdateSubscriber updateSubscriber);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteSubscriberById(@PathVariable Long id);

}
