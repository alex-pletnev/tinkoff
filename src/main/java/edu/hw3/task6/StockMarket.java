package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket implements IStockMarket {
    PriorityQueue<IStock> stocks = new PriorityQueue<>(Comparator.comparingDouble(IStock::getPrice).reversed());

    @Override
    public void add(IStock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(IStock stock) {
        stocks.remove(stock);
    }

    @Override
    public IStock mostValuableStock() {
        return stocks.peek();
    }
}
