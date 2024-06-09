package com.jvk.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    class Product {

    }

    private BigDecimal totalValue = BigDecimal.ZERO; // 반올림 이슈 회피용
    private Map<Product, Integer> basket = new HashMap<>();

    private boolean invariant() { // 불변식 검사를 위한 invariant 메서드 
        return totalValue.compareTo(BigDecimal.ZERO) >= 0;
    }
    public void add(Product product, int qtyToAdd) {
        assert invariant() : "Invariant does not hold";
        assert product != null : "Product is required"; // 제품이 널이 아님을 보장하는 사전 조건
        assert qtyToAdd > 0 : "Quantity must be greater than 0"; // 0보다 큼을 보장하는 사전 조건
        BigDecimal oldTotalValue = totalValue;

        // 제품을 추가하고, 합계를 갱신

        assert basket.containsKey(product) : "Product was not inserted in the basket";
        // 제품이 장바구니에 추가되었음을 보장하는 사후 조건

        assert totalValue.compareTo(oldTotalValue) == 1 :
            "Total value must be greater than the previous total value";
    }

    public void remove(Product Product, int qtyToRemove) {
        // 제품을 제거하고, 합계를 갱신
    }

    public void remove(Product product) {
        assert product != null : "product can't be null";
        assert basket.containsKey(product) : "Product must already be in the basket";

        //장바구니에서 제품을 빼고 합계를 갱신한다.

        assert !basket.containsKey(product) : "Product is still int the basket";
    }

}
