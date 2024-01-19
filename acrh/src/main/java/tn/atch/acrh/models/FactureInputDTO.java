package tn.atch.acrh.models;

import java.util.List;

public record FactureInputDTO(
        Long clientId, List<Long> productPurchasedIds,String paymentMethod
        ) {
}
