package edu.project3.util;

import edu.project3.analyzer.GeneralInformationAnalyser;
import edu.project3.analyzer.RequestedResourcesAnalyser;
import edu.project3.analyzer.ResponseCodesAnalyser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private PrintUtil() {
    }

    public static void printGeneralInformation(GeneralInformationAnalyser analyser) {
        LOGGER.info("## Общая информация ##");
        LOGGER.info("Файл(-ы) {}", analyser.getSource());
        LOGGER.info("Начальная дата {}", analyser.getStartDate());
        LOGGER.info("Конечная дата {}", analyser.getFinishDate());
        LOGGER.info("Количество запросов {}", analyser.getRequestCount());
        LOGGER.info("Средний размер ответа {}", analyser.averageResponseSize());
    }

    public static void printRequestedResources(RequestedResourcesAnalyser analyser, int limit) {
        LOGGER.info("## Запрашиваемые ресурсы ##");
        int tear = 1;
        var resList = analyser.getMostPopularResources();
        int index = 0;
        for (var res : resList) {
            if (index > limit) {
                break;
            }
            index++;
            LOGGER.info("{}. {} {}", tear++, res.getKey(), res.getValue());
        }
    }

    //    ResponseCodes
    public static void printResponseCodes(ResponseCodesAnalyser analyser, int limit) {
        LOGGER.info("## Коды ответа ##");
        int tear = 1;
        var resList = analyser.getMostPopularResponseCodes();
        int index = 0;
        for (var res : resList) {
            if (index > limit) {
                break;
            }
            index++;
            var key = res.getKey();
            var description = ResponseCodesAnalyser.RESPONSE_CODES_DESCRIPTION.get(key);
            var value = res.getValue();
            LOGGER.info("{}. {} {} {}", tear++, key, description, value);
        }
    }

}

