package edu.project3;

import com.beust.jcommander.Parameter;

public class Arguments {
    @Parameter(names = {"--path", "-p"})
    private String path;
    @Parameter(names = {"--format", "-f"})
    private String format;
    @Parameter(names = {"--from"})
    private String from;
    @Parameter(names = {"--to"})
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPath() {
        return path;
    }

    public String getFormat() {
        return format;
    }
}
