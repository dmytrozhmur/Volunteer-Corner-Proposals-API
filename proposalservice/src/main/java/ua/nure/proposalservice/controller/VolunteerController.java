package ua.nure.proposalservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.dto.VolunteerInfo;
import ua.nure.proposalservice.service.VolunteerService;

import java.util.List;

@RestController
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/api/v1/volunteers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all volunteers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Got volunteer list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerInfo[].class)))
    })
    public List<VolunteerInfo> allVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/api/v1/volunteers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get volunteer by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Got volunteer list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerInfo.class))),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = @Content(mediaType = "application/json"))
    })
    public VolunteerInfo oneVolunteer(@PathVariable String id) {
        return volunteerService.getVolunteerById(id);
    }
}
