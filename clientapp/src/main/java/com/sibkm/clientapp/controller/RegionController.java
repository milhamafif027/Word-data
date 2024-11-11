package com.sibkm.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sibkm.clientapp.entity.Region;
import com.sibkm.clientapp.service.RegionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    // Get All Region
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("isActive", "region");
        return "/region/index"; 
    }

    // Get By Id
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model){
        model.addAttribute("region", regionService.getByid(id));
        model.addAttribute("isActive", "region");
        return "region/detail-form";
    }

    // Create View
    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("region", new Region());
        model.addAttribute("isActive", "region");
        return "region/create-form";
    }

    // Create Region
    @PostMapping
    public String create(Region region) {
        regionService.create(region);
        return "redirect:/region";
    }

    // Update View
    @GetMapping("update/{id}")
    public String updateView(@PathVariable Integer id, Region region, Model model){
        model.addAttribute("region", regionService.getByid(id));
        model.addAttribute("isActive", "region");
        return "region/update-form";
    }

    // Update Region
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Region region){
        regionService.update(id, region);
        return "redirect:/region";
    }

    // Delete Region
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, Region region){
        regionService.delete(id);
        return "redirect:/region";
    }
}
