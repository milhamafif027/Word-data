package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.Country;
import com.sibkm.serverapp.model.request.CountryRequest;
import com.sibkm.serverapp.model.response.CountryResponse;
import com.sibkm.serverapp.service.CountryService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController 
@AllArgsConstructor
@RequestMapping("/country")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CountryController {

  private CountryService countryService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
  public List<Country> getAll() {
    return countryService.getAll();
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAuthority('READ_ADMIN')")
  public Country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  // Custom Respon
  @GetMapping("/res/{id}")
  @PreAuthorize("hasAuthority('READ_ADMIN')")
  public CountryResponse getByIdCustomeResponse(@PathVariable Integer id) {
    return countryService.getByIdCustomeResponse(id);
  }

  // Custom Respon (map)
  @GetMapping("/map/{id}")
  @PreAuthorize("hasAuthority('READ_ADMIN')")
  public Map<String, Object> getByIdCustomMap(@PathVariable Integer id) {
    return countryService.getByIdCustomMap(id);
  }

  // Without DTO
  @PostMapping
  public Country create(@RequestBody Country country) {
    return countryService.create(country);
  }

  // With DTO Manual
  @PostMapping("/dto")
  @PreAuthorize("hasAuthority('CREATE_ADMIN')")
  public Country createDTOManual(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTOManual(countryRequest);
  }

  // With DTO Otomatis
  @PostMapping("/dtootomatis")
  @PreAuthorize("hasAuthority('CREATE_ADMIN')")
  public Country createDTOOtomatis(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTOOtomatis(countryRequest);
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
  public Country update(
    @PathVariable Integer id,
    @RequestBody Country country
  ) {
    return countryService.update(id, country);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
  public Country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }

  
}