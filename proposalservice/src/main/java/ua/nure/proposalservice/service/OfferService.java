package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.exception.ApiRequestException;
import ua.nure.proposalservice.mapper.ProposalCreationMapper;
import ua.nure.proposalservice.mapper.ProposalInfoMapper;
import ua.nure.proposalservice.dao.OfferRepository;
import ua.nure.proposalservice.dto.ProposalCreation;
import ua.nure.proposalservice.dto.ProposalInfo;
import ua.nure.proposalservice.model.HelpProposal;

import java.util.List;

@Service
public class OfferService {
    private static final String NOT_FOUND = "Proposal not found";
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ProposalInfoMapper infoMapper;
    @Autowired
    private ProposalCreationMapper creationMapper;

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

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or #entity.id == null or #entity.owner.user.login == authentication.principal.username")
    private ProposalInfo saveProposalAndGetDto(ProposalCreation editedProposal, HelpProposal entity) {
        creationMapper.toProposal(editedProposal, entity);
        offerRepository.save(entity);
        return infoMapper.toDto(entity);
    }
}
