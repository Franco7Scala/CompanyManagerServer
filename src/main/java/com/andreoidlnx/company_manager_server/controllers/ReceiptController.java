package com.andreoidlnx.company_manager_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.andreoidlnx.company_manager_server.entities.Receipt;
import com.andreoidlnx.company_manager_server.services.ReceiptService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<?> addReceipt(@Valid @RequestBody Receipt receipt){
        try{
            receiptService.addReceipt(receipt);
            return new ResponseEntity<>("RECEIPT_CREATED", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("RECEIPT_NOT_CREATED", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Receipt> getAllReceipt(){
        return receiptService.getAllReceipts();
    }

}

/*
 * public String setProducts(List<ProductTransition> products) {
        receipt.setTotal(0);
        List<ProductInReceipt> content = new LinkedList<>();
        for ( ProductTransition currentProduct : products ) {
            ProductInReceipt toAdd = new ProductInReceipt();
            toAdd.setPrice(currentProduct.getProductDetail().getPricePrivate() * currentProduct.getQuantity());
            toAdd.setProductDetail(currentProduct.getProductDetail());
            toAdd.setQuantity(currentProduct.getQuantity());
            content.add(toAdd);
            receipt.setTotal(receipt.getTotal() + toAdd.getPrice());
        }
        receipt.setProductInReceiptList(content);
        receipt.setTotalDiscounted(receipt.getTotal());
        return null;
    }

    public String processInsertion() {
        receipt.setUser(accountingManagement.getCurrentUser());
        receipt.setTotalDiscounted((float) (receipt.getTotal() - (receipt.getDiscount() * 0.01 * receipt.getTotal())));
        receiptManagement.addReceipt(receipt);
        return null;
    }
 */