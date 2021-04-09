package ent.app.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CardServiceEndpoint {
    private static final String NAMESPACE_URI = "http://app.ent/webservice";

    @Autowired
    private CardRepository cardRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCardChargeRequest")
    @ResponsePayload
    public GetCardChargeResponse getCountry(@RequestPayload GetCardChargeRequest request) {
        GetCardChargeResponse response = new GetCardChargeResponse();
        if (cardRepository.isCardValid(request.getNumber())) {
            response.setResponse("Credit Card Charged Success");
        } else {
            response.setResponse("Card Declined!!! Invalid Card Number");
        }
        return response;
    }
}
