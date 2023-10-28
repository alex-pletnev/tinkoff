package edu.hw3.task6;

public interface IStockMarket {
    /** Добавить акцию */
    void add(IStock stock);
    /** Удалить акцию */
    void remove(IStock stock);
    /** Самая дорогая акция */
    IStock mostValuableStock();
}
