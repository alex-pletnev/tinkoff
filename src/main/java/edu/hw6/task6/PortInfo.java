package edu.hw6.task6;

import org.jetbrains.annotations.NotNull;

public record PortInfo(int port, String protocol, String description) implements Comparable<PortInfo> {

    @Override
    public int compareTo(@NotNull PortInfo o) {
        return Integer.compare(port(), o.port());
    }
}
