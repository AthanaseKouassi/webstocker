package com.webstocker.service.impl;

import com.webstocker.service.LigneBudgetService;
import com.webstocker.domain.LigneBudget;
import com.webstocker.repository.LigneBudgetRepository;
import com.webstocker.repository.search.LigneBudgetSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing LigneBudget.
 */
@Service
@Transactional
public class LigneBudgetServiceImpl implements LigneBudgetService{

    private final Logger log = LoggerFactory.getLogger(LigneBudgetServiceImpl.class);
    
    @Inject
    private LigneBudgetRepository ligneBudgetRepository;
    
    @Inject
    private LigneBudgetSearchRepository ligneBudgetSearchRepository;
    
    /**
     * Save a ligneBudget.
     * 
     * @param ligneBudget the entity to save
     * @return the persisted entity
     */
    public LigneBudget save(LigneBudget ligneBudget) {
        log.debug("Request to save LigneBudget : {}", ligneBudget);
        LigneBudget result = ligneBudgetRepository.save(ligneBudget);
        ligneBudgetSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the ligneBudgets.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<LigneBudget> findAll(Pageable pageable) {
        log.debug("Request to get all LigneBudgets");
        Page<LigneBudget> result = ligneBudgetRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one ligneBudget by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LigneBudget findOne(Long id) {
        log.debug("Request to get LigneBudget : {}", id);
        LigneBudget ligneBudget = ligneBudgetRepository.findOne(id);
        return ligneBudget;
    }

    /**
     *  Delete the  ligneBudget by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LigneBudget : {}", id);
        ligneBudgetRepository.delete(id);
        ligneBudgetSearchRepository.delete(id);
    }

    /**
     * Search for the ligneBudget corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LigneBudget> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of LigneBudgets for query {}", query);
        return ligneBudgetSearchRepository.search(queryStringQuery(query), pageable);
    }
}
