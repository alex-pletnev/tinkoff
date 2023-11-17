package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {

    private Task4() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void createFileAndFillIt(Path path, String fill) {
        if (Objects.isNull(path)) {
            throw new NullPointerException("Path arg mustn't be 'null'");
        }
        try (
            var outputStream = Files.newOutputStream(path);
            var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            var printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.print(fill);
            printWriter.flush();
        } catch (IOException e) {
            LOGGER.error("Problem in", e);
        }
    }
}
