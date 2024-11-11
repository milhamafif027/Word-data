package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Country;
import com.sibkm.serverapp.entity.Region;
import com.sibkm.serverapp.model.request.CountryRequest;
import com.sibkm.serverapp.model.response.CountryResponse;
import com.sibkm.serverapp.repository.CountryRepository;
import com.sibkm.serverapp.repository.RegionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CountryService {

  private CountryRepository countryRepository;
  private RegionRepository regionRepository;
  private RegionService regionService;

  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  public Country getById(Integer Id) {
    return countryRepository
      .findById(Id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Country not found!!!"
        )
      );
  }

  // Custom Response
  public CountryResponse getByIdCustomeResponse(Integer id) {
    Country country = countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Country not found!!!"
        )
      );
    CountryResponse countryResponse = new CountryResponse();

    countryResponse.setCountryId(country.getId());
    countryResponse.setCountryCode(country.getCode());
    countryResponse.setCountryName(country.getName());
    countryResponse.setRegionId(country.getRegion().getId());
    countryResponse.setRegionName(country.getRegion().getName());
    return countryResponse;
  }

  // Custom Response with MAP
  public Map<String, Object> getByIdCustomMap(Integer id) {
    Country country = countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Country not found!!!"
        )
      );
    Map<String, Object> result = new HashMap<>();

    result.put("cId", country.getId());
    result.put("cCode", country.getCode());
    result.put("cName", country.getName());
    result.put("rId", country.getRegion().getId());
    result.put("rName", country.getRegion().getName());

    return result;
  }

  // Without DTO
  public Country create(Country country) {
     /**
     * Challenge: Kenapa bisa dan tidak bisa memasukkan nama yang sama dengan region?
     * 1. bug:
     * Terjadi bug karena method findByNameOrRegionName hanya membandingkan country dengan region yang diinputkan.
     * sehingga apabila menginputkan country yang sama dengan nama region lain yang tidak diinputkan akan tetap lolos.
     * 2. penyebab:
     * method tidak membandingkan inputan country dengan region lain yang tidak diinputkan.
     * 3. cara solve?
     * membandingkan inputan country dengan seluruh data yang ada di region satu per satu menggunakan perulangan.
     */
     if(!countryRepository.findByNameOrRegionName(country.getName(), country.getRegion().getName()).isEmpty()){
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Name is already exists!!!");
     }

     // Cek apakah nama country sama dengan nama region manapun
    List<Region> regions = regionRepository.findAll(); // Ambil semua region
    for (Region region : regions) {
        if (region.getName().equalsIgnoreCase(country.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name cant be the sama with region name!!!");
        }
    }
    return countryRepository.save(country); 
  }

  // With DTO
  public Country createDTOManual(CountryRequest countryRequest) {

    Country country = new Country();
    country.setCode(countryRequest.getCode());
    country.setName(countryRequest.getName());

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  // With DTO Otomatis
  public Country createDTOOtomatis(CountryRequest countryRequest) {
    Country country = new Country();
    BeanUtils.copyProperties(countryRequest, country);

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  public Country update(Integer id, Country country) {
    getById(id);
    country.setId(id);
    return countryRepository.save(country);
  }

  public Country delete(Integer id) {
    Country country = getById(id);
    countryRepository.delete(country);
    return country;
  }
}
