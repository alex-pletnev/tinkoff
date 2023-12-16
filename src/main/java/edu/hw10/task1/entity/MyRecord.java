package edu.hw10.task1.entity;

import edu.hw10.task1.anotation.Max;
import edu.hw10.task1.anotation.Min;
import edu.hw10.task1.anotation.NotNull;

public record MyRecord(
    @Max(value = 1000)
    Integer fieldMax,
    @Min(value = -1000)
    Integer fieldMin,
    @NotNull @Max(100)
    String fieldNotNull,
    String justField
) {
}
