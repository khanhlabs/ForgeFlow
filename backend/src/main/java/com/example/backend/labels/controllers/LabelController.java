package com.example.backend.labels.controllers;

import com.example.backend.labels.dto.CreateLabelRequest;
import com.example.backend.labels.dto.LabelResponse;
import com.example.backend.labels.dto.UpdateLabelRequest;
import com.example.backend.labels.services.LabelService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelService labelService;

    public LabelController(
            LabelService labelService
    ) {
        this.labelService = labelService;
    }

    //CREATE LABEL
    @PostMapping
    public LabelResponse createLabel(
            @Valid @RequestBody CreateLabelRequest request
    ){
        return labelService.createLabel(request);
    }

    //GET ALL LABELS
    @GetMapping
    public List<LabelResponse> getAllLabels(){
        return labelService.getAllLabels();
    }

    //GET ALL LABEL
    @GetMapping("/projects/{projectId}")
    public List<LabelResponse> getAllLabelsByProject(
            @PathVariable Long projectId
    ){
        return labelService.getLabelsByProject(projectId);
    }

    //UPDATE LABEL
    @PatchMapping("/{labelId}")
    public LabelResponse updateLabel(
            @PathVariable Long labelId,
            @Valid @RequestBody UpdateLabelRequest request
    ){
        return labelService.updateLabel(labelId, request);
    }

    //DELETE LABEL
    @DeleteMapping("/{labelId}")
    public void deleteLabel(
            @PathVariable Long labelId
    ){
        labelService.deleteLabel(labelId);
    }

}
