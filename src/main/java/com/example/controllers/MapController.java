package com.example.controllers;

import com.example.model.dto.PointDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.fasterxml.jackson.core.io.NumberInput.parseDouble;

@Controller
public class MapController {

    @GetMapping("/map")
    public String map(Model model,@RequestParam(name = "coordinates", required = false) String coordinates) {
        model.addAttribute("coordinates",coordinates);
        return "map/map";
    }


    @GetMapping("/options")
    public String options(Model model, @RequestParam(name = "lng", required = false) String lng,
                          @RequestParam(name = "lat", required = false) String lat) {
        PointDto selectedPoint = new PointDto(parseDouble(lat),parseDouble(lng),"selectedPoint");
        model.addAttribute("coordinates", selectedPoint);
        return "map/options";
    }

}
