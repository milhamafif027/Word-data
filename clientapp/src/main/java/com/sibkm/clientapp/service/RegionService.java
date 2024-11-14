package com.sibkm.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.Region;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionService {

  @Value("${server.base.url}/region")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  // Get All
  public List<Region> getAll() {
    return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Region>>() {
            })
        .getBody();
  }

  // Region count
  public int countRegions() {
    return getAll().size();
  }

  // Get By Id
  public Region getByid(Integer id) {
    log.info("endpoint serverapp = {}", url.concat("/" + id));
    return restTemplate
        .exchange(url.concat("/" + id), HttpMethod.GET, null, Region.class)
        .getBody();
  }

  // Create
  public Region create(Region region) {
    return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<Region>(region),
            new ParameterizedTypeReference<Region>() {
            })
        .getBody();
  }

  // Update
  public Region update(Integer id, Region region) {
    HttpEntity<Region> request = new HttpEntity<Region>(region);
    return restTemplate
        .exchange(url.concat("/" + id), HttpMethod.PUT, request, Region.class)
        .getBody();
  }

  // Delete
  public Region delete(Integer id) {
    return restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null, Region.class)
        .getBody();
  }

}
