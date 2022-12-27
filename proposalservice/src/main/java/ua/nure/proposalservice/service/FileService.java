package ua.nure.proposalservice.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.specialized.BlockBlobClient;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dao.PhotoRepository;
import ua.nure.proposalservice.dto.PhotoInfo;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.mapper.PhotoInfoMapper;
import ua.nure.proposalservice.model.HelpProposalPhoto;
import ua.nure.proposalservice.properties.FileStorageProperties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Log4j2
@Service
public class FileService {
    private final Path fileStorageLocation;
    private final BlobServiceClient blobServiceClient;
    private final PhotoInfoMapper infoMapper;
    private final OfferRepository offerRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public FileService(@NonNull FileStorageProperties fileStorageProperties,
                       BlobServiceClient blobServiceClient,
                       PhotoInfoMapper infoMapper,
                       OfferRepository offerRepository,
                       PhotoRepository photoRepository) {
        fileStorageLocation = Path.of(fileStorageProperties.getUploadDir());
        this.blobServiceClient = blobServiceClient;
        this.infoMapper = infoMapper;
        this.offerRepository = offerRepository;
        this.photoRepository = photoRepository;
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            log.error("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public PhotoInfo uploadAndDownloadFile(@NonNull MultipartFile file, String containerName, String proposalId) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        String filename = file.getOriginalFilename();
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(filename).getBlockBlobClient();
        try {
            saveToAzure(file, filename, blockBlobClient);
            return saveAndGetDto(blobContainerClient, filename, proposalId);
        } catch (IOException e) {
            log.error("Error while processing file {}", e.getLocalizedMessage());
            return null;
        }
    }

    private PhotoInfo saveAndGetDto(BlobContainerClient blobContainerClient, String filename, String proposalId) {
        HelpProposalPhoto proposalPhoto = new HelpProposalPhoto();
        proposalPhoto.setId(UUID.randomUUID().toString());
        proposalPhoto.setFilePath(blobContainerClient.getBlobContainerUrl() + "/" + filename);
        proposalPhoto.setCreatedAt(LocalDateTime.now());
        proposalPhoto.setHelpProposal(offerRepository.findById(proposalId)
                .orElseThrow(() -> new ApiRequestException("Proposal not found")));

        photoRepository.save(proposalPhoto);
        return infoMapper.toDto(proposalPhoto);
    }

    private void saveToAzure(MultipartFile file, String filename, BlockBlobClient blockBlobClient) throws IOException {
        if (blockBlobClient.exists()) {
            blockBlobClient.delete();
        }
        blockBlobClient.upload(new BufferedInputStream(file.getInputStream()), file.getSize(), true);
        String tempFilePath = fileStorageLocation + "/" + filename;
        Files.deleteIfExists(Paths.get(tempFilePath));
        blockBlobClient.downloadToFile(new File(tempFilePath).getPath());
    }


    private @NonNull BlobContainerClient getBlobContainerClient(@NonNull String containerName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }
        return blobContainerClient;
    }
}
