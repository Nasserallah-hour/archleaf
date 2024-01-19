package tn.atch.acrh.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class BuyProductsForm {
    private Long selectedClient;
    private List<Integer> selectedQuantities;

    // Getters and setters

    public Long getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Long selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<Integer> getSelectedQuantities() {
        return selectedQuantities;
    }

    public void setSelectedQuantities(List<Integer> selectedQuantities) {
        this.selectedQuantities = selectedQuantities;
    }
}
