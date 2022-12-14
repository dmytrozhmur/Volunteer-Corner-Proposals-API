package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.dao.HelpProposalResponseRepository;
import ua.nure.proposalservice.dto.ProposalResponseCreation;
import ua.nure.proposalservice.exception.ApiException;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.dao.UserRepository;
import ua.nure.proposalservice.dao.VolunteerRepository;
import ua.nure.proposalservice.mapper.ProposalCreationMapper;
import ua.nure.proposalservice.mapper.ProposalInfoMapper;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.mapper.ProposalResponseCreationMapper;
import ua.nure.proposalservice.model.HelpProposal;
import ua.nure.proposalservice.model.HelpProposalResponse;
import ua.nure.proposalservice.model.User;
import ua.nure.proposalservice.model.Volunteer;

import java.util.List;

@Service
public class OfferService {
    private static final String NOT_FOUND = "Proposal not found";
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private HelpProposalResponseRepository responseRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private ProposalInfoMapper infoMapper;
    @Autowired
    private ProposalCreationMapper proposalCreationMapper;
    @Autowired
    private ProposalResponseCreationMapper responseCreationMapper;

    public ProposalInfo getProposalById(String id) {
        return infoMapper.toDto(offerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(NOT_FOUND)));
    }

    public List<ProposalInfo> getAllProposals() {
        return infoMapper.toDtoList(offerRepository.findAll());
    }

    public ProposalInfo addProposal(ProposalCreation creation) {
        HelpProposal entity = HelpProposal.empty();
        return saveProposalAndGetDto(creation, entity);
    }

    public ProposalInfo updateProposalById(String id, ProposalCreation editedProposal) {
        HelpProposal entity = offerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(NOT_FOUND));
        return saveProposalAndGetDto(editedProposal, entity);
    }

    public void updateProposalStatusById(String id, int status) {
        HelpProposal entity = offerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(NOT_FOUND));
        entity.setStatus(status);
        offerRepository.save(entity);
    }

    public List<ProposalInfo> getCurrProposals() {
        UserDetails currUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Volunteer currEntity = volunteerRepository.findByUserLogin(currUser.getUsername());
        return infoMapper.toDtoList(offerRepository.findAllByOwner(currEntity));
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or #entity.id == null or #entity.owner.user.login == authentication.principal.username")
    private ProposalInfo saveProposalAndGetDto(ProposalCreation editedProposal, HelpProposal entity) {
        proposalCreationMapper.toEntity(editedProposal, entity);
        offerRepository.save(entity);
        return infoMapper.toDto(entity);
    }

    public void addResponse(String id, ProposalResponseCreation creation) {
        HelpProposal proposal = offerRepository.findById(id).orElseThrow();
        HelpProposalResponse entity = responseCreationMapper.toEntity(creation);
        entity.setToProposal(proposal);
        responseRepository.save(entity);
    }
}
