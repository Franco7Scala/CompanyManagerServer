package com.andreoidlnx.company_manager_server.services.products;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andreoidlnx.company_manager_server.entities.Product;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.ProductState;
import com.andreoidlnx.company_manager_server.entities.ProductTransition;
import com.andreoidlnx.company_manager_server.entities.State;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.repositories.ProductDetailRepository;
import com.andreoidlnx.company_manager_server.repositories.ProductRepository;
import com.andreoidlnx.company_manager_server.repositories.ProductStateRepository;
import com.andreoidlnx.company_manager_server.repositories.ProductTransitionRepository;
import com.andreoidlnx.company_manager_server.repositories.StateRepository;



@Service
public class ProductsManagementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductStateRepository productStateRepository;

    @Autowired
    private ProductTransitionRepository productsTransitionRepository;

    @Autowired
    private StateRepository stateRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addProduct(Product product) {
        if ( product.getBarCodeSingle().equals("") ) {
            product.setBarCodeSingle(null);
        }
        if ( product.getBarCodePackage().equals("") ) {
            product.setBarCodePackage(null);
        }
        productRepository.save(product);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addProductDetail(ProductDetail productDetail, State state, User user) {
        if ( productDetailRepository.find(productDetail.getProductDetailPK()) == null ) {
            for ( State currentState : stateRepository.findAll() ) {
                if ( currentState.getName().equals(state.getName()) ) {
                    productDetail.getProductStateList().add(new ProductState(productDetail.getProduct().getId(), productDetail.getProductDetailPK().getYear(), currentState.getName(), productDetail.getQuantity()));
                }
                else {
                    productDetail.getProductStateList().add(new ProductState(productDetail.getProduct().getId(), productDetail.getProductDetailPK().getYear(), currentState.getName(), 0));
                }
            }
            productDetailRepository.save(productDetail);
        }
        else {
            ProductDetail productDetailEdited = productDetailRepository.find(productDetail.getProductDetailPK());
            productDetailEdited.setQuantity(productDetailEdited.getQuantity() + productDetail.getQuantity());
            productDetailRepository.edit(productDetailEdited);
            ProductState productState = productStateRepository.findBy(productDetail.getProductDetailPK().getIdProduct(), productDetail.getProductDetailPK().getYear(), state.getName());
            if ( productState != null ) {
                productState.setQuantity(productState.getQuantity() + productDetail.getQuantity());
                productStateRepository.edit(productState);
            }
            else {
                productStateRepository.create(new ProductState(productDetail.getProductDetailPK().getIdProduct(), productDetail.getProductDetailPK().getYear(), state.getName(), productDetail.getQuantity()));
            }
        }
        ProductTransition transition = new ProductTransition();
        transition.setFromState(new State(Constants.DEFAULT_STATE));
        transition.setToState(state);
        transition.setProductDetail(productDetail);
        transition.setQuantity(productDetail.getQuantity());
        transition.setTransitionDate(new Date());
        transition.setDescription(Constants.DEFAULT_STATE);
        transition.setIdUser(user);
        productsTransitionRepository.create(transition);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editProductDetail(ProductDetail productDetail) {
        if ( productDetail.getProduct().getBarCodeSingle().equals("") ) {
            productDetail.getProduct().setBarCodeSingle(null);
        }
        if ( productDetail.getProduct().getBarCodePackage().equals("") ) {
            productDetail.getProduct().setBarCodePackage(null);
        }
        productRepository.edit(productDetail.getProduct());
        productDetailRepository.edit(productDetail);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addProductState(ProductState state) {
        productStateRepository.create(state);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTransition(ProductTransition transition) {
        productsTransitionRepository.create(transition);
        for ( ProductState productState : transition.getProductDetail().getProductStateList() ) {
            if ( productState.getState().equals(transition.getFromState()) ) {
                productState.setQuantity(productState.getQuantity() - transition.getQuantity());
                productStateRepository.edit(productState);
            }
            else if ( productState.getState().equals(transition.getToState()) ) {
                productState.setQuantity(productState.getQuantity() + transition.getQuantity());
                productStateRepository.edit(productState);
            }
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDetail> getProductDetailByBarCodePackage(String barCode, boolean onlyAvailable) {
        Product product = productRepository.findByBarCodePackage(barCode);
        return getProducts(product, onlyAvailable);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDetail> getProductDetailByBarCodeSingle(String barCode, boolean onlyAvailable) {
        Product product = productRepository.findByBarCodeSingle(barCode);
        return getProducts(product, onlyAvailable);
    }

    private List<ProductDetail> getProducts(Product product, boolean onlyAvailable) {
        if ( product == null ) {
            return null;
        }
        else {
            List<State> downloadableStates = stateRepository.findAllDownloadable();
            if ( onlyAvailable ) {
                List<ProductDetail> results = productDetailRepository.findByProductNameAvailable(product.getName());
                List<ProductDetail> toDelete = new LinkedList<>();
                for ( ProductDetail detail : results ) {
                    boolean found = false;
                    for ( ProductState state : detail.getProductStateList() ) {
                        if ( downloadableStates.contains(state.getState()) && state.getQuantity() > 0 ) {
                            found = true;
                            break;
                        }
                    }
                    if ( !found ) {
                        toDelete.add(detail);
                    }
                }
                results.removeAll(toDelete);
                return results;
            }
            else {
                return productDetailRepository.findByProductName(product.getName());
            }
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductTransition> getProductTransitions() {
        return productsTransitionRepository.findAddOrdered();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDetail> getAllProductsDetail() {
        return productDetailRepository.findAllOrdered();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDetail> getFullProductsDetailByProductName(String name) {
        return productDetailRepository.findByProductName(name);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ProductDetail> getProductsDetailByProductName(String name) {
        List<ProductDetail> result = productDetailRepository.findByProductName(name);
        List<ProductDetail> toDelete = new LinkedList<>();
        for ( int i = 0; i < result.size(); i++ ) {
            ProductDetail current = result.get(i);
            boolean ok = false;
            for ( int j = 0; j < current.getProductStateList().size(); j++ ) {
                if ( current.getProductStateList().get(j).getState().getDownloadable() ) {
                    if ( current.getProductStateList().get(j).getQuantity() > 0 ) {
                        ok = true;
                        break;
                    }
                }
            }
            if ( !ok ) {
                toDelete.add(current);
            }
        }
        for ( ProductDetail current : toDelete ) {
            result.remove(current);
        }
        return result;
    }

}
