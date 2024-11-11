
package com.sibkm.clientapp.controller.rest;

import com.sibkm.clientapp.entity.Region;
import com.sibkm.clientapp.service.RegionService;
import java.util.List;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/region")
public class RestRegionController {
    private RegionService regionService;

    // Get All
    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

    // Get By Id
    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id) {
        return regionService.getByid(id);
    }

    // Create
    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionService.create(region);
    }

    // Update
    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region) {
        return regionService.update(id, region);
    }

    // Delete
    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id) {
        return regionService.delete(id);
    }
}