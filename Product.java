package com.fawry.ecommerce;

public final class Product {

        private String name;
        private double price;
        private int quantity;
        private boolean expires;
        private boolean requiresShipping;
        private double weight;

        public Product(String name, double price, int quantity,
                       boolean expires, boolean requiresShipping, double weight) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.expires = expires;
            this.requiresShipping = requiresShipping;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }
        public double getPrice()
        {
            return price;
        }
        public int getQuantity()
        {
            return quantity;
        }
        public boolean isExpires() {
            return expires;
        }
        public boolean requiresShipping() {
            return requiresShipping;
        }
        public double getWeight() {
            return weight;
        }

        public void decreaseQuantity(int amount)  {
            if (amount > quantity)
                System.out.println("Not enough " + name + " in stock");
            quantity -= amount;
        }

        public double calculateTotalWeight(int quantity) {
            if (requiresShipping) {
                return weight * quantity;
            }
            return 0;
        }
    }


