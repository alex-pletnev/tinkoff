package edu.hw3.task6;

public interface IStockMarket {

    void add(IStock stock);

    void remove(IStock stock);

    IStock mostValuableStock();
}
