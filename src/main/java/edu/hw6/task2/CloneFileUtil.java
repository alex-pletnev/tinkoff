package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CloneFileUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private CloneFileUtil() {
    }

    public static Path cloneFile(Path path) throws IOException {
        if (Objects.isNull(path)) {
            LOGGER.error("Exception, path to clone, can't be null");
            return null;
        }
        if (!Files.exists(path)) {
            return Files.createFile(path);
        }

        Path parentDir = path.getParent();
        String postfix = " — копия.txt";
        String copyFileName = path.getFileName().toString().replace(".txt", postfix);
        int index = 2;
        Path copyPath = parentDir.resolveSibling(copyFileName);
        while (Files.exists(copyPath)) {
            String newPostfix = " — копия (" + index++ + ").txt";
            copyFileName = copyFileName.replace(postfix, newPostfix);
            postfix = newPostfix;
            copyPath = parentDir.resolveSibling(copyFileName);
        }
        Files.copy(path, copyPath);
        return copyPath;
    }
}
