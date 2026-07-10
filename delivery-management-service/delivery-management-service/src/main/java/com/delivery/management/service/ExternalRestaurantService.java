package com.delivery.management.service;

import com.delivery.management.exception.ExternalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalRestaurantService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${restaurant.management.base-url}")
    private String restaurantManagementBaseUrl;

    public boolean isRestaurantActive(Long restaurantId) {
        String url = restaurantManagementBaseUrl + "/api/restaurants/" + restaurantId + "/active";
        try {
            Boolean active = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(active);
        } catch (RestClientException ex) {
            throw new ExternalServiceException(
                    "Failed to check restaurant " + restaurantId
                            + " status from restaurant-management-service: " + ex.getMessage());
        }
    }
}
