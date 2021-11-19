package dev.huai.models;

import org.springframework.stereotype.Component;

@Component
public class Amount {

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
