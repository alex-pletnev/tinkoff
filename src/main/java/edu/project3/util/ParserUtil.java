package edu.project3.util;

import edu.project3.entity.LogLine;
import edu.project3.entity.RequestType;
import edu.project3.exception.ParseException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private ParserUtil() {
    }

    public static LogLine logLineFromString(String s) {
        Pattern ipPattern = Pattern.compile("(((\\d{1,3}\\.){3}\\d{1,3})|((\\w{0,4}:){4,6}\\w{0,4}))");
        Pattern datePattern = Pattern.compile("(\\d\\d/\\w+/\\d{4}:\\d\\d:\\d\\d:\\d\\d [+\\-]\\d\\d\\d\\d)");
        Pattern requestTypePattern = Pattern.compile("(GET|HEAD|TRACE|OPTIONS|POST|DELETE)");
        Pattern resourcePattern = Pattern.compile(" (/\\w+/\\w+) ");
        Pattern responsePattern = Pattern.compile(" (\\d{3}) (\\d+) ");
        Matcher ipMatcher = ipPattern.matcher(s);
        Matcher dateMatcher = datePattern.matcher(s);
        Matcher requestTypeMatcher = requestTypePattern.matcher(s);
        Matcher resourceMatcher = resourcePattern.matcher(s);
        Matcher responseMatcher = responsePattern.matcher(s);
        if (!(ipMatcher.find() && dateMatcher.find() && requestTypeMatcher.find() && resourceMatcher.find()
            && responseMatcher.find())) {
            LOGGER.error("'{}' does not comply with the NGINX Log Schema "
                + "''$remote_addr - $remote_user [$time_local] ' '\"$request\" "
                + "$status $body_bytes_sent ' '\"$http_referer\" \"$http_user_agent\"''", s);
            throw new ParseException();
        }
        String ip = ipMatcher.group();
        String dateFormat = "dd/MMM/yyyy:HH:mm:ss X";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateMatcher.group(), dateTimeFormatter);
        RequestType requestType = switch (responseMatcher.group()) {
            case "HEAD" -> RequestType.HEAD;
            case "TRACE" -> RequestType.TRACE;
            case "OPTIONS" -> RequestType.OPTIONS;
            case "POST" -> RequestType.POST;
            case "DELETE" -> RequestType.DELETE;
            default -> RequestType.GET;
        };
        String resource = resourceMatcher.group();
        int responseCode;
        int responseSize;
        try {
            responseCode = Integer.parseInt(responseMatcher.group(1));
            responseSize = Integer.parseInt(responseMatcher.group(2));
        } catch (NumberFormatException e) {
            LOGGER.error("responseCode and responseSize must be integer", e);
            throw new ParseException();
        }
        return new LogLine(ip, offsetDateTime, requestType, resource, responseCode, responseSize);
    }

}
