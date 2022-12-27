package ua.nure.proposalservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.dto.ProposalResponseCreation;
import ua.nure.proposalservice.service.OfferService;

import java.util.List;

@CrossOrigin
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
    public List<ProposalInfo> allProposals() {
        return offerService.getAllProposals();
    }

    @PostMapping("/api/v1/proposals/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new proposal")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proposal created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "400", description = "Body not properly specified",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json"))
    })
    public ProposalInfo newProposal(@RequestBody ProposalCreation newProposal) {
        return offerService.addProposal(newProposal);
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
    public ProposalInfo oneProposal(@PathVariable String id) {
        return offerService.getProposalById(id);
    }

    @PutMapping("/api/v1/proposals/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update proposal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Proposal edited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "400", description = "Body not properly specified",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    public ProposalInfo editProposal(@PathVariable String id,
                                     @RequestBody ProposalCreation editedProposal) {
        return offerService.updateProposalById(id, editedProposal);
    }

    @PostMapping("/api/v1/proposals/{id}/close")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Set proposal status to 3000 by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Proposal status edited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    public void completeProposal(@PathVariable String id) {
        offerService.updateProposalStatusById(id, 3000);
    }

    @PostMapping("/api/v1/proposals/{id}/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Set proposal status to 2000 by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Proposal status edited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    public void cancelProposal(@PathVariable String id) {
        offerService.updateProposalStatusById(id, 2000);
    }

    @PostMapping("/api/v1/proposals/{id}/reactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Set proposal status to 1000 by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Proposal status edited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    public void restartProposal(@PathVariable String id) {
        offerService.updateProposalStatusById(id, 1000);
    }

    @PostMapping("/api/v1/proposals/{id}/reply")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Respond to volunteer's proposal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Proposal status edited",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo.class))),
            @ApiResponse(responseCode = "400", description = "Body not properly specified",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Proposal not found",
                    content = @Content(mediaType = "application/json"))
    })
    public void respondToProposal(@PathVariable String id,
                                  @RequestBody ProposalResponseCreation creation) {
        offerService.addResponse(id, creation);
    }

    @GetMapping("/api/v1/proposals/current")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get my proposals")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Got list of current user's proposals",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalInfo[].class))),
            @ApiResponse(responseCode = "401", description = "Unknown sender",
                    content = @Content(mediaType = "application/json"))
    })
    public List<ProposalInfo> myProposals() {
        return offerService.getCurrProposals();
    }
}
