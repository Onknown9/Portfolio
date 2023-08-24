package com.example.project.controllers;

import com.example.project.models.PhotoDescription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {
    @GetMapping("/")
    public String HomePage(Model model) {
        int maxPhotos = 2;
        int currentPhotoIndex = 1;
        int direction = 1;


        currentPhotoIndex += direction;

        if (currentPhotoIndex < 1) {
            currentPhotoIndex = maxPhotos;
        } else if (currentPhotoIndex > maxPhotos) {
            currentPhotoIndex = 1;
        }

        String[] photoDescriptions = PhotoDescription.getPr1();
        String currentDescription = photoDescriptions[currentPhotoIndex - 1];

        model.addAttribute("currentPhotoIndex", currentPhotoIndex);
        model.addAttribute("maxPhotos", maxPhotos);
        model.addAttribute("currentDescription", currentDescription);

        return "HomePage";
    }
}
