package ua.nure.proposalservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.proposalservice.dto.PhotoInfo;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.model.HelpProposalPhoto;
import ua.nure.proposalservice.service.FileService;
import ua.nure.proposalservice.service.OfferService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class FileController {

    private final FileService fileService;
    private final OfferService offerService;

    @Autowired
    public FileController(FileService fileService, OfferService offerService) {
        this.fileService = fileService;
        this.offerService = offerService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update proposal by id")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Photos added",
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
    @PostMapping(value = "/api/v1/proposals/{id}/images")
    public List<PhotoInfo> uploadAndDownload(@PathVariable String id, @RequestParam("files") MultipartFile[] files) {
        try {
            List<PhotoInfo> result = new ArrayList<>();
            for (MultipartFile file : files) {
                PhotoInfo photo;
                if ((photo = fileService.uploadAndDownloadFile(file, "images", id)) != null) {
//                    final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
//                            .getFileStorageLocation() + "/" + file.getOriginalFilename())));
                    result.add(photo);
                }
                else throw new ApiRequestException("Error while processing file");
            }
            return result;
        } catch (Exception e) {
            throw new ApiRequestException("Error while processing file");
        }
    }
}
