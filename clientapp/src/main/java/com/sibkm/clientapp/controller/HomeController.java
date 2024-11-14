package com.sibkm.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sibkm.clientapp.service.CountryService;
import com.sibkm.clientapp.service.RegionService;

@Controller
public class HomeController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @GetMapping("/home")
    public String index(Model model) {
        // Mengambil jumlah region dan country
        model.addAttribute("regionCount", regionService.countRegions());
        model.addAttribute("countryCount", countryService.countCountry());

        model.addAttribute("name", "sibkm");
        model.addAttribute("isActive", "home");
        return "index";
    }
}