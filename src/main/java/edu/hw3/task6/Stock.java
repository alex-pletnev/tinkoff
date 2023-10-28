package edu.hw3.task6;

import java.util.Objects;

public class Stock implements IStock {
    private double price;

    public Stock(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Double.compare(stock.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
