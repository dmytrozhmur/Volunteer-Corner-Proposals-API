package ua.nure.proposalservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PhotoInfo {
    private String id;
    private String proposalId;
    private String filePath;
    private String createdAt;
    private String modifiedAt;
}
