package tn.atch.acrh.FactureService.model;

import tn.atch.acrh.ProductService.model.Product;

import java.util.List;

public record FactureInputDTO(
        Long clientId, List<Long> productPurchasedIds,String paymentMethod
        ) {
}
