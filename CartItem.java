package com.fawry.ecommerce;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int additionalQuantity) throws Exception {
        if (additionalQuantity > product.getQuantity()) {
            System.out.println("Not enough " + product.getName() + " in stock");
        }
        quantity += additionalQuantity;
    }
}
