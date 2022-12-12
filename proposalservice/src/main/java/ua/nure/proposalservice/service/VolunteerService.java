package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.dto.VolunteerInfo;
import ua.nure.proposalservice.mapper.VolunteerInfoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerInfoMapper infoMapper;

    public List<VolunteerInfo> getAllVolunteers() {
        return volunteerRepository.findAll().stream()
                .map(infoMapper::toDto)
                .collect(Collectors.toList());
    }
}
