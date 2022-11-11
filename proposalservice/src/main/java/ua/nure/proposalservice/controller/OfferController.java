package ua.nure.proposalservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.service.OfferService;

import java.util.List;

@RestController
public class OfferController {
    private OfferService offerService;

    public OfferController(@Autowired OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/api/v1/proposals")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all proposals")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Got proposal list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo[].class)))
    })
    private List<ProposalInfo> allProposals() {
        return offerService.getAllProposals();
    }

    @PostMapping("/api/v1/proposals/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new proposal")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proposal created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "400", description = "Body not specified",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json"))
    })
    private ProposalInfo newProposal(@RequestBody ProposalCreation creation) {
        return offerService.addProposal(creation);
    }

    @GetMapping("/api/v1/proposals/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get proposal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Got proposal",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    private ProposalInfo oneProposal(@PathVariable String id) {
        return offerService.getProposalById(id);
    }
}
