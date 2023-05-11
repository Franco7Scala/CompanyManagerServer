package com.andreoidlnx.company_manager_server.services.products;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.andreoidlnx.company_manager_server.entities.ProductInReceipt;
import com.andreoidlnx.company_manager_server.entities.Receipt;
import com.andreoidlnx.company_manager_server.entities.keys.ProductInReceiptPK;
import com.andreoidlnx.company_manager_server.repositories.ProductInReceiptRepository;
import com.andreoidlnx.company_manager_server.repositories.ReceiptRepository;

@Service
public class ReceiptManagmentService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ProductInReceiptRepository productInReceiptRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addReceipt(Receipt receipt) {
        List<ProductInReceipt> content = receipt.getProductInReceiptList();
        receipt.setProductInReceiptList(null);
        receipt.setReceiptDate(new Date());
        receipt.setDailyNumber((receiptRepository.getLatestTodayNumber(receipt.getReceiptDate()) + 1));
        receiptRepository.save(receipt); //
        for ( ProductInReceipt current : content ) {
            current.setProductInReceiptPK(new ProductInReceiptPK(receipt.getId(), current.getProductDetail().getProduct().getId(), current.getProductDetail().getProductDetailPK().getYear()));
            productInReceiptRepository.save(current); //
        }
        receipt.setProductInReceiptList(content);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAllOrderedByDate(); //
    }
    
}
