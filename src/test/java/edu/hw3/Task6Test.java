package edu.hw3;

import edu.hw3.task6.IStock;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Task6Test {
    private StockMarket stockMarket;

    @BeforeEach
    public void setUp() {
        stockMarket = new StockMarket();
    }
    @Test
    void testAdd() {
        IStock stock = new Stock(100);

        stockMarket.add(stock);

        assertEquals(stock, stockMarket.mostValuableStock());
    }

    @Test
    void testRemove() {
        IStock stock = new Stock(100);

        stockMarket.add(stock);
        stockMarket.remove(stock);

        assertNull(stockMarket.mostValuableStock());
    }

    @Test
    void testMostValuableStock() {
        for (double i = 0; i < 100; i++) {
            stockMarket.add(new Stock(i));
        }


        IStock result = stockMarket.mostValuableStock();
        IStock excepted = new Stock(99);

        assertEquals(excepted.getPrice(), result.getPrice());
    }
}
