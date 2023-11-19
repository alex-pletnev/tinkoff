package edu.project3.entity;

import java.time.OffsetDateTime;

public record LogLine(String ip, OffsetDateTime date, RequestType requestType, String resource, int responseCode,
                      int responseSize) {

}
