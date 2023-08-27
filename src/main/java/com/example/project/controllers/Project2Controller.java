package com.example.project.controllers;

import com.example.project.models.PhotoDescription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Project2Controller {
    @GetMapping("/project2")
    public String homePage(@RequestParam(name = "currentPhotoIndex", defaultValue = "1") int currentPhotoIndex, Model model) {

        int maxPhotos = 5; // Set your maxPhotos value

        if (currentPhotoIndex < 1) {
            currentPhotoIndex = maxPhotos;
        } else if (currentPhotoIndex > maxPhotos) {
            currentPhotoIndex = 1;
        }

        // Get photo descriptions
        String[] photoDescriptions = PhotoDescription.getPr2();
        String currentDescription = photoDescriptions[currentPhotoIndex - 1];

        String imagePath = String.format("/images/project2/pr%d.png", currentPhotoIndex);

        model.addAttribute("currentPhotoIndex", currentPhotoIndex);
        model.addAttribute("maxPhotos", maxPhotos);
        model.addAttribute("currentDescription", currentDescription);
        model.addAttribute("imagePath", imagePath);

        return "Project2"; // Return the name of your Thymeleaf template
    }
    @PostMapping("/changePhoto2")
    public String changePhoto(
            @RequestParam(name = "currentPhotoIndex") int currentPhotoIndex,
            @RequestParam(name = "direction") int direction,
            Model model) {
    // Calculate new photo index
        int maxPhotos = PhotoDescription.getPr2().length;
        currentPhotoIndex += direction;
        if (currentPhotoIndex < 1) {
            currentPhotoIndex = maxPhotos;
        } else if (currentPhotoIndex > maxPhotos) {
            currentPhotoIndex = 1;
        }

        // Get photo descriptions
        String[] photoDescriptions = PhotoDescription.getPr2();
        String currentDescription = photoDescriptions[currentPhotoIndex - 1];

        // Construct the image path
        String imagePath = String.format("/images/project2/pr%d.png", currentPhotoIndex);

        model.addAttribute("currentPhotoIndex", currentPhotoIndex);
        model.addAttribute("maxPhotos", maxPhotos);
        model.addAttribute("currentDescription", currentDescription);
        model.addAttribute("imagePath", imagePath);

        return "Project2"; // Return the name of your Thymeleaf template
    }
}
