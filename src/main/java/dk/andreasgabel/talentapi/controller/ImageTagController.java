package dk.andreasgabel.talentapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageTagController {

    @Value("${IMAGE_TAG:latest}")
    private String imageTag;

    @GetMapping("/api/image-tag")
    public Map<String, String> getImageTag() {
        return Map.of("tag", imageTag);
    }
}
