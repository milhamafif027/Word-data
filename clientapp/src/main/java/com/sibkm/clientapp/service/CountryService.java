package com.sibkm.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.Country;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryService {

    @Value("${server.base.url}/country")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All
    public List<Country> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
        }).getBody();
    }

    // Get By Id
    public Country getById(Integer id) {
        log.info("endpoint serverapp = {}", url.concat("/" + id));
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.GET, null, Country.class)
                .getBody();
    }

    // Create
    public Country create(Country country){
        return restTemplate
            .exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<Country>(country),
                new ParameterizedTypeReference<Country>() {
                })
            .getBody();
    }

    // Update
    public Country update(Integer id, Country country){
        HttpEntity<Country> request = new HttpEntity<Country>(country);
        return restTemplate
          .exchange(url.concat("/" + id), HttpMethod.PUT, request, Country.class)
          .getBody();
    }

    // delete
    public Country delete(Integer id){
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null, Country.class)
        .getBody();
    }

}
