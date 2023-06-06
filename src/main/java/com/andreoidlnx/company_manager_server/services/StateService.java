package com.andreoidlnx.company_manager_server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.ProductState;
import com.andreoidlnx.company_manager_server.entities.State;
import com.andreoidlnx.company_manager_server.repositories.ProductDetailRepository;
import com.andreoidlnx.company_manager_server.repositories.ProductStateRepository;
import com.andreoidlnx.company_manager_server.repositories.StateRepository;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired private ProductStateRepository productStateRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addState(State state) {
        if(stateRepository.findById(state.getName()) != null) {
            state.setVisible(true);
            stateRepository.save(state);
        } 
        else {
            stateRepository.save(state);
        }
        for (ProductDetail currentProductDetail : productDetailRepository.findAll()) {
            if (productStateRepository.findByProductIdAndYearAndState(currentProductDetail.getProductDetailPK().getIdProduct(), currentProductDetail.getProductDetailPK().getYear(), state.getName()) == null) {
                productStateRepository.save(new ProductState(currentProductDetail.getProductDetailPK().getIdProduct(), currentProductDetail.getProductDetailPK().getYear(), state.getName(), 0));
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setPreferredState(State state) {
        List<State> allStates = stateRepository.findAll();
        for(State currentState : allStates) {
            if(currentState.isFavourite()) {
                currentState.setFavourite(false);
                stateRepository.save(currentState);
            }

        }
        state.setFavourite(true);
        stateRepository.save(state);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setDownloadableState(State state, boolean value) {
        state.setDownloadable(value);
        stateRepository.save(state);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteState(State state) {
        state.setVisible(false);
        stateRepository.save(state);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<State> getPreferredState() {
        return stateRepository.findPreferred();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getQuantityForState(ProductDetail product, State state) {
        return productStateRepository.getQuantityForState(product.getProduct().getId(), product.getProductDetailPK().getYear(), state.getName());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<State> getAllVisibleStates() {
        return stateRepository.findAllVisible();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<State> getAllDownloadables() {
        return stateRepository.findAllDownloadable();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<State> getAllUndownloadables() {
        return stateRepository.findAllUnownloadable();
    }

}
