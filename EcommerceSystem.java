package com.fawry.ecommerce;
import java.util.List;

public class EcommerceSystem {

    public static void main(String[] args) {
        // Create products
        Product cheese = new Product("Cheese", 100, 5, true, true, 0.2);
        Product tv = new Product("TV", 1000, 3, false, true, 15.0);
        Product biscuits = new Product("Biscuits", 150, 20, true, false, 0.7);
        Product mobile = new Product("Mobile", 800, 10, false, false, 0.0);
        Product scratchCard = new Product("Scratch Card", 50, 15, false, false, 0.0);

        Customer customer = new Customer("John", 2000);
        Cart cart = new Cart();
        try {
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(biscuits, 3);
            checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Process checkout
    static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty())
            System.out.println("Cart is empty");

        double subtotal = cart.calculateSubtotal();
        double shippingFee = cart.calculateShippingFee();
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total)
            System.out.println("Customer's balance is insufficient");

        // Process payment
        customer.deductBalance(total);

        // Print shipment notice if needed
        List<CartItem> itemsToShip = cart.getItemsRequiringShipping();
        if (!itemsToShip.isEmpty()) {
            System.out.println("** Shipment notice **");
            double totalWeight = 0;

            for (CartItem item : itemsToShip) {
                totalWeight += item.getProduct().calculateTotalWeight(item.getQuantity());
                System.out.printf("%dx %s\t%dg\n",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        (int)(item.getProduct().getWeight() * 1000 * item.getQuantity()));


            }

            System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
        }

        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s\t%d\n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    (int)(item.getProduct().getPrice() * item.getQuantity()));
        }

        System.out.println("--------------------");
        System.out.printf("Subtotal\t%d\n", (int)subtotal);
        System.out.printf("Shipping\t%d\n", (int)shippingFee);
        System.out.printf("Amount\t\t%d\n", (int)total);

        cart.clear();
    }
}

