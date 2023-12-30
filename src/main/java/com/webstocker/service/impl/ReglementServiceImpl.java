package com.webstocker.service.impl;

import com.webstocker.domain.BonDeSortie;
import com.webstocker.domain.Facture;
import com.webstocker.domain.LigneBonDeSortie;
import com.webstocker.domain.Reglement;
import com.webstocker.domain.enumeration.newfeature.StatutFacture;
import com.webstocker.repository.BonDeSortieRepository;
import com.webstocker.repository.FactureRepository;
import com.webstocker.repository.LigneBonDeSortieRepository;
import com.webstocker.repository.ReglementRepository;
import com.webstocker.repository.search.ReglementSearchRepository;
import com.webstocker.service.ReglementService;
import com.webstocker.service.util.WebstockerDateFormat;
import com.webstocker.utilitaires.PremierEtDernierJourDuMois;
import com.webstocker.web.rest.dto.newfeature.ReglementDto;
import com.webstocker.web.rest.dto.newfeature.ReglementFactureDto;
import com.webstocker.web.rest.mapper.newfeature.DetailFactureMapper;
import com.webstocker.web.rest.mapper.newfeature.ReglementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Reglement.
 */
@Service
@Transactional
public class ReglementServiceImpl implements ReglementService {

    private final Logger log = LoggerFactory.getLogger(ReglementServiceImpl.class);
    @Inject
    private BonDeSortieRepository bonDeSortieRepository;

    @Inject
    private ReglementRepository reglementRepository;
    @Inject
    private LigneBonDeSortieRepository ligneBonDeSortieRepository;
    @Inject
    private ReglementSearchRepository reglementSearchRepository;
    @Inject
    private FactureRepository factureRepository;
    @Inject
    private WebstockerDateFormat webstockerDateFormat;
    @Autowired
    private DetailFactureMapper detailFactureMapper;
    @Autowired
    private ReglementMapper reglementMapper;


    public Reglement save(Reglement reglement) {
        log.debug("Request to save Reglement : {}", reglement);
        return reglementRepository.save(reglement);
    }

    /**
     * Get all the reglements.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Reglement> findAll() {
        log.debug("Request to get all Reglements");
        return reglementRepository.findAll();
    }

    /**
     * Get one reglement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Reglement findOne(Long id) {
        log.debug("Request to get Reglement : {}", id);
        return reglementRepository.findOne(id);
    }

    /**
     * Delete the  reglement by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Reglement : {}", id);
        reglementRepository.delete(id);
    }

    /**
     * Search for the reglement corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Reglement> search(String query) {
        log.debug("Request to search Reglements for query {}", query);
        return StreamSupport
            .stream(reglementSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public List<Reglement> getTousLesReglementDuMois(String maDate) {

        String dateDebut;
        String dateFin;

        PremierEtDernierJourDuMois premierEtDernier = new PremierEtDernierJourDuMois(maDate);
        dateDebut = premierEtDernier.getDateDebutDuMois(maDate);
        dateFin = premierEtDernier.getDateFinDuMois(maDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate debut = LocalDate.parse(dateDebut, formatter);
        LocalDate fin = LocalDate.parse(dateFin, formatter);

        return reglementRepository.findByDateReglementBetween(debut, fin);
    }

    @Override
    public List<Reglement> findByFacture(Facture facture) {
        return reglementRepository.findByFacture(facture);
    }

    @Override
    public void reglementFacture(Facture facture, String dateReglement) {
        BonDeSortie bonDeSortie = bonDeSortieRepository.findOne(facture.getBonDeSortie().getId());
        if (bonDeSortie != null && "CASH".equals(bonDeSortie.getTypeVente().toString())) {
            reglementFactureCash(bonDeSortie, facture, dateReglement);
        }
    }

    @Override
    public ReglementFactureDto reglementFactureCredit(Long idFacture, List<Reglement> reglements) {
        ReglementFactureDto reglementFactureDto = new ReglementFactureDto();
        List<ReglementDto> listReglment = new ArrayList<>();
        Facture facture = factureRepository.findOne(idFacture);
        BigDecimal totalRegle = BigDecimal.ZERO;
        for (Reglement reg : reglements) {
            Reglement reglement = new Reglement();
            reglement.setProduit(reg.getProduit());
            reglement.setFacture(facture);
            reglement.setDateReglement(reg.getDateReglement());
            reglement.setMontantReglement(reg.getMontantReglement());

            totalRegle = totalRegle.add(BigDecimal.valueOf(reg.getMontantReglement()));

            reglementRepository.save(reglement);
            factureRepository.updateStatutFacture(StatutFacture.SOLDE.toString(), facture.getId());
            listReglment.add(reglementMapper.reglementToReglementDto(reglement));
        }

        reglementFactureDto.setIdFacture(idFacture);
        reglementFactureDto.setReglementDtos(listReglment);
        reglementFactureDto.setTotalFacture(totalRegle);

        return reglementFactureDto;
    }

    private void reglementFactureCash(BonDeSortie bonDeSortie, Facture facture, String dateReglement) {

        List<LigneBonDeSortie> ligneBonDeSorties = ligneBonDeSortieRepository.findAllByBonDeSortie(bonDeSortie);
        for (LigneBonDeSortie lbs : ligneBonDeSorties) {
            Reglement reglement = new Reglement();
            reglement.setProduit(lbs.getProduit());
            reglement.setFacture(facture);
            reglement.setDateReglement(webstockerDateFormat.convertirStingToLocalDate(dateReglement));
            reglement.setMontantReglement(lbs.getPrixDeVente());

            reglementRepository.save(reglement);
            factureRepository.updateStatutFacture(StatutFacture.SOLDE.toString(), facture.getId());
        }
    }


}
