package com.sibkm.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sibkm.clientapp.entity.Country;
import com.sibkm.clientapp.service.CountryService;
import com.sibkm.clientapp.service.RegionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;
    private RegionService regionService;

    // Get All
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("countryCount", countryService.countCountry());
        model.addAttribute("isActive", "country");
        return "country/index";
    }

    // Get By Id
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("country", countryService.getById(id));
        model.addAttribute("isActive", "country");
        return "country/detail-form";
    }

    // Create View
    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "country");
        return "country/create-form";
    }

    // Create Country
    @PostMapping
    public String create(Country country) {
        countryService.create(country);
        return "redirect:/country";
    }

    // Update View
    @GetMapping("/update/{id}")
    public String updateView(@PathVariable Integer id, Country country, Model model) {
        model.addAttribute("country", countryService.getById(id));
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "country");
        return "country/update-form";
    }

    // Update Country
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Country country) {
        countryService.update(id, country);
        return "redirect:/country";
    }

    // Delete
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id, Country country) {
        countryService.delete(id);
        return "redirect:/country";
    }

}
