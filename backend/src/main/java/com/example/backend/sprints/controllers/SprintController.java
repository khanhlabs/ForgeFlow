package com.example.backend.sprints.controllers;

import com.example.backend.sprints.dto.CreateSprintRequest;
import com.example.backend.sprints.dto.SprintResponse;
import com.example.backend.sprints.dto.UpdateSprintRequest;
import com.example.backend.sprints.services.SprintService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(
            SprintService sprintService
    ) {
        this.sprintService = sprintService;
    }

    //CREATE SPRINT
    @PostMapping
    public SprintResponse createSprint(
            @Valid @RequestBody CreateSprintRequest request
    ) {
        return sprintService.createSprint(request);
    }

    //GET ALL SPRINT
    @GetMapping
    public List<SprintResponse> getAllSprints(){
        return sprintService.getAllSprints();
    }

    //GET SPRINT BY ID
    @GetMapping("/{sprintId}")
    public SprintResponse getSprintById(@PathVariable Long sprintId) {
        return sprintService.getSprintById(sprintId);
    }

    //UPDATE SPRINT NAME
    @PatchMapping("/{sprintId}/projects/{projectId}")
    public SprintResponse updateSprint(
            @PathVariable Long sprintId,
            @PathVariable Long projectId,
            @Valid @RequestBody UpdateSprintRequest request
    ){
        return sprintService.updateSprintName(sprintId, projectId, request);
    }

    //DELETE SPRINT
    @DeleteMapping("/{sprintId}")
    public void deleteSprint(@PathVariable Long sprintId) {
        sprintService.deleteSprint(sprintId);
    }
}
