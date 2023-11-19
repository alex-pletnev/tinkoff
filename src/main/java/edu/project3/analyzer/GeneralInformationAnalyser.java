package edu.project3.analyzer;

import edu.project3.entity.LogLine;
import java.util.List;

public class GeneralInformationAnalyser {

    private final List<LogLine> logLineList;
    private final String source;

    public GeneralInformationAnalyser(List<LogLine> logLineList, String source) {
        this.logLineList = logLineList;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public String getStartDate() {
        return getDate(0);
    }

    public String getFinishDate() {
        return getDate(logLineList.size() - 1);
    }

    public int getRequestCount() {
        return logLineList.size();
    }

    public long averageResponseSize() {
        long sum = 0;
        for (var logLine : logLineList) {
            sum += logLine.responseSize();
        }
        return sum / logLineList.size();
    }

    private String getDate(int index) {
        var date = logLineList.get(index).date();
        StringBuilder sb = new StringBuilder();
        var splitter = ".";
        sb.append(date.getDayOfMonth())
            .append(splitter)
            .append(date.getMonthValue())
            .append(splitter)
            .append(date.getYear());
        return sb.toString();
    }

}
