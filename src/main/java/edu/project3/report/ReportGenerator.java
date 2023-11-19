package edu.project3.report;

import edu.project3.analyzer.GeneralInformationAnalyser;
import edu.project3.analyzer.RequestedResourcesAnalyser;
import edu.project3.analyzer.ResponseCodesAnalyser;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportGenerator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MD_2TABLE_HEADER_LINE = "|:---------------------:|-------------:|\n";
    private static final String MD_3TABLE_HEADER_LINE = "|:---:|:---------------------:|-----------:|\n";
    private static final String ADOC_TABLE_BORDER = "|===\n";

    private static final int LIMIT = 3;
    private final GeneralInformationAnalyser generalInformationAnalyser;
    private final RequestedResourcesAnalyser requestedResourcesAnalyser;
    private final ResponseCodesAnalyser responseCodesAnalyser;

    public ReportGenerator(
        GeneralInformationAnalyser generalInformationAnalyser,
        RequestedResourcesAnalyser requestedResourcesAnalyser,
        ResponseCodesAnalyser responseCodesAnalyser
    ) {
        this.generalInformationAnalyser = generalInformationAnalyser;
        this.requestedResourcesAnalyser = requestedResourcesAnalyser;
        this.responseCodesAnalyser = responseCodesAnalyser;
    }

    public void generateMdReport() {
        Path path = Paths.get("report.md");

        try (PrintWriter printWriter = new PrintWriter(path.toFile())) {
            printWriter.print("#### Общая информация\n");
            printWriter.print("| Метрика | Значение |\n");
            printWriter.print(MD_2TABLE_HEADER_LINE);
            printWriter.print("| Файл(-ы) |'" + generalInformationAnalyser.getSource() + "'|\n");
            printWriter.print("| Начальная дата |" + generalInformationAnalyser.getStartDate() + "|\n");
            printWriter.print("| Конечная дата |" + generalInformationAnalyser.getFinishDate() + "|\n");
            printWriter.print("| Количество запросов |" + generalInformationAnalyser.getRequestCount() + "|\n");
            printWriter.print("| Средний размер ответа |" + generalInformationAnalyser.averageResponseSize() + "b|\n");
            printWriter.println();
            printWriter.print("#### Запрашиваемые ресурсы\n");
            printWriter.print("| Место | Ресурс | Количество |\n");
            printWriter.print(MD_3TABLE_HEADER_LINE);
            int tear = 1;
            var resList = requestedResourcesAnalyser.getMostPopularResources();
            int index = 0;
            for (var res : resList) {
                if (index > LIMIT) {
                    break;
                }
                index++;
                printWriter.print("|" + tear++ + "|'" + res.getKey() + "'|" + res.getValue() + "|\n");
            }

            printWriter.println();
            printWriter.print("#### Коды ответа\n");
            printWriter.print("| Код | Имя | Количество |\n");
            printWriter.print(MD_3TABLE_HEADER_LINE);
            var codes = responseCodesAnalyser.getMostPopularResponseCodes();
            codes.forEach(
                code -> printWriter.print("|" + code.getKey() + "|'"
                    + ResponseCodesAnalyser.RESPONSE_CODES_DESCRIPTION.get(code.getKey()) + "'|" + code.getValue()
                    + "|\n")
            );
            printWriter.println();
            printWriter.flush();

        } catch (IOException e) {
            LOGGER.error("Can't generate .md report", e);
        }
    }

    public void generateAdocReport() {
        Path path = Paths.get("report.adoc");

        try (PrintWriter printWriter = new PrintWriter(path.toFile())) {
            printWriter.print("== Общая информация\n");
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.print("| Метрика | Значение \n");
            printWriter.print("|Файл(-ы)|'" + generalInformationAnalyser.getSource() + "'\n");
            printWriter.print("|Начальная дата|" + generalInformationAnalyser.getStartDate() + "\n");
            printWriter.print("|Конечная дата|" + generalInformationAnalyser.getFinishDate() + "\n");
            printWriter.print("|Количество запросов|" + generalInformationAnalyser.getRequestCount() + "\n");
            printWriter.print("|Средний размер ответа|" + generalInformationAnalyser.averageResponseSize() + "b\n");
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.println();
            printWriter.print("== Запрашиваемые ресурсы\n");
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.print("| Место | Ресурс | Количество \n");
            int tear = 1;
            var resList = requestedResourcesAnalyser.getMostPopularResources();
            int index = 0;
            for (var res : resList) {
                if (index > LIMIT) {
                    break;
                }
                index++;
                printWriter.print("|" + tear++ + "|'" + res.getKey() + "'|" + res.getValue() + "\n");
            }
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.println();
            printWriter.print("== Коды ответа\n");
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.print("| Код | Имя | Количество \n");
            var codes = responseCodesAnalyser.getMostPopularResponseCodes();
            codes.forEach(
                code -> printWriter.print("|" + code.getKey() + "|'"
                    + ResponseCodesAnalyser.RESPONSE_CODES_DESCRIPTION.get(code.getKey()) + "'|" + code.getValue()
                    + "\n")

            );
            printWriter.print(ADOC_TABLE_BORDER);
            printWriter.println();
            printWriter.flush();

        } catch (IOException e) {
            LOGGER.error("Can't generate .adoc report", e);
        }
    }
}
