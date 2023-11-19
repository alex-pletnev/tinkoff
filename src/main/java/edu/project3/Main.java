package edu.project3;

import com.beust.jcommander.JCommander;
import edu.project3.analyzer.GeneralInformationAnalyser;
import edu.project3.analyzer.RequestedResourcesAnalyser;
import edu.project3.analyzer.ResponseCodesAnalyser;
import edu.project3.client.HttpLogs;
import edu.project3.entity.LogLine;
import edu.project3.report.ReportGenerator;
import edu.project3.util.LogLineValidator;
import edu.project3.util.ParserUtil;
import edu.project3.util.PrintUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {

    private static final int LIMIT = 3;

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
            .addObject(arguments)
            .build()
            .parse(args);
        HttpLogs httpLogs = new HttpLogs();
        var strLogsList = httpLogs.getLogs(arguments.getPath());
        List<LogLine> logLineList = new ArrayList<>();
        strLogsList.forEach(
            str -> {
                var logLine = ParserUtil.logLineFromString(str);
                if (LogLineValidator.validateLine(logLine, arguments)) {
                    logLineList.add(logLine);
                }
            }
        );
        GeneralInformationAnalyser generalInformationAnalyser
            = new GeneralInformationAnalyser(logLineList, arguments.getPath());
        RequestedResourcesAnalyser requestedResourcesAnalyser
            = new RequestedResourcesAnalyser(logLineList);
        ResponseCodesAnalyser responseCodesAnalyser
            = new ResponseCodesAnalyser(logLineList);
        var format = arguments.getFormat();
        if (!Objects.isNull(format)) {
            ReportGenerator reportGenerator
                = new ReportGenerator(generalInformationAnalyser, requestedResourcesAnalyser, responseCodesAnalyser);
            if (format.equals("markdown")) {
                reportGenerator.generateMdReport();
            }

            if (format.equals("adoc")) {
                reportGenerator.generateAdocReport();
            }

        }

        PrintUtil.printGeneralInformation(generalInformationAnalyser);
        PrintUtil.printRequestedResources(requestedResourcesAnalyser, LIMIT);
        PrintUtil.printResponseCodes(responseCodesAnalyser, LIMIT);
    }

}
