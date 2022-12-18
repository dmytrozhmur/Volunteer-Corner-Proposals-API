package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.VolunteerInfo;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.mapper.VolunteerInfoMapper;
import ua.nure.proposalservice.model.Volunteer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerService {
    public static final String NOT_FOUND = "Volunteer not found";
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerInfoMapper infoMapper;

    public List<VolunteerInfo> getAllVolunteers() {
        return volunteerRepository.findAll().stream()
                .map(infoMapper::toDto)
                .collect(Collectors.toList());
    }

    public VolunteerInfo getVolunteerById(String id) {
        return volunteerRepository.findById(id).map(infoMapper::toDto)
                .orElseThrow(() -> new ApiRequestException(NOT_FOUND));
    }

    public void makeApproved(String id) {
        Volunteer entity = volunteerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(NOT_FOUND));
        entity.setApproved(true);
        volunteerRepository.save(entity);
    }
}
