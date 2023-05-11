package com.andreoidlnx.company_manager_server.services.products;

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
public class StatesManagmentService {

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ProductStateRepository productStateRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addState(State state) {
        if (stateRepository.findById(state.getName()) != null) { //
            state.setVisible(true);
            stateRepository.save(state); //
        } 
        else {
            stateRepository.save(state); //
        }
        for (ProductDetail currentProductDetail : productDetailRepository.findAll()) {
            if (productStateRepository.findByProductIdAndYearAndState(currentProductDetail.getProductDetailPK().getIdProduct(), currentProductDetail.getProductDetailPK().getYear(), state.getName()) == null) { //
                productStateRepository.save(new ProductState(currentProductDetail.getProductDetailPK().getIdProduct(), currentProductDetail.getProductDetailPK().getYear(), state.getName(), 0)); //
            }
        }
    }
}
