package com.fawry.ecommerce;


import java.util.ArrayList;
import java.util.List;

class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) throws Exception  {
        if (quantity <= 0)
            System.out.println("Quantity must be positive");
        if (quantity > product.getQuantity())
            System.out.println("Not enough " + product.getName() + " in stock");

        // Check if product is already in cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.increaseQuantity(quantity);
                return;
            }
        }

        // Add new item to cart
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    public double calculateShippingFee() {
        double totalWeight = 0;
        for (CartItem item : items) {
            if (item.getProduct().requiresShipping()) {
                totalWeight += item.getProduct().getWeight() * item.getQuantity();
            }
        }
        return totalWeight * 30; // 30 per kg
    }

    public List<CartItem> getItemsRequiringShipping() {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().requiresShipping()) {
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }

    public void clear() {
        items.clear();
    }
}
