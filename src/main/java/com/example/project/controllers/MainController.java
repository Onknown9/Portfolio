package com.example.project.controllers;

import com.example.project.models.PhotoDescription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    int maxPhotos = 2; // Set your maxPhotos value
    // Construct the image path
    String imagePath = String.format("/images/project1/pr%d.png", 1);
    @GetMapping("/")
    public String homePage(@RequestParam(name = "projectName", defaultValue = "pr1") String projectName,
                           @RequestParam(name = "currentPhotoIndex", defaultValue = "1") int currentPhotoIndex,
                           Model model) {


        if (currentPhotoIndex < 1) {
            currentPhotoIndex = maxPhotos;
        } else if (currentPhotoIndex > maxPhotos) {
            currentPhotoIndex = 1;
        }

        // Get photo descriptions
        String[] photoDescriptions = PhotoDescription.getPr1();
        String currentDescription = photoDescriptions[currentPhotoIndex - 1];


        model.addAttribute("currentPhotoIndex", currentPhotoIndex);
        model.addAttribute("maxPhotos", maxPhotos);
        model.addAttribute("currentDescription", currentDescription);

        return "HomePage"; // Return the name of your Thymeleaf template
    }


    @PostMapping("/changePhoto")
    public String changePhoto(
            @RequestParam(name = "currentPhotoIndex") int currentPhotoIndex,
            @RequestParam(name = "direction") int direction,
            Model model) {

        currentPhotoIndex += direction;
        if (currentPhotoIndex < 1) {
            currentPhotoIndex = maxPhotos;
        } else if (currentPhotoIndex > maxPhotos) {
            currentPhotoIndex = 1;
        }

        // Get photo descriptions
        String[] photoDescriptions = PhotoDescription.getPr1();
        String currentDescription = photoDescriptions[currentPhotoIndex - 1];


        model.addAttribute("currentPhotoIndex", currentPhotoIndex);
        model.addAttribute("maxPhotos", maxPhotos);
        model.addAttribute("currentDescription", currentDescription);
        model.addAttribute("imagePath", imagePath);

        return "HomePage"; // Return the name of your Thymeleaf template
    }
}
