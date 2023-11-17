package edu.hw6.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;

class DiskMapTest {
    private Path path;
    private DiskMap diskMap;

    @BeforeEach
    public void setUp() throws IOException {
        path = Files.createTempFile(null, null);
        diskMap = new DiskMap(new HashMap<>(), path);
    }

    @Test
    void shouldThrowExceptionWhenFileDataIsIllegal() throws IOException {
        Files.writeString(path, "Hello, world!");
        System.out.println();
        assertThatThrownBy(() -> diskMap.loadFromDisk())
            .isInstanceOf(LoadFromDiskException.class);
    }

    @Test
    void shouldPutToDelegateAndFlushToDisk() {
        String key = "testKey";
        String value = "42";

        diskMap.put(key, value);

        assertAll(
            () -> assertThat(diskMap.getDelegate()).containsEntry(key, value),
            () -> assertFileContains(Map.of(key, value))
        );
    }

    @Test
    void shouldRemoveFromDelegateAndFlushToDisk() {
        String key = "testKey";
        String value = "42";
        diskMap.put(key, value);

        diskMap.remove(key);

        assertAll(
            () -> assertThat(diskMap.getDelegate()).doesNotContainKey(key),
            () -> assertFileIsEmpty()
        );
    }

    @Test
    void shouldPutAllToDelegateAndFlushToDisk() {
        Map<String, String> map = Map.of("key1", "1", "key2", "2");

        diskMap.putAll(map);

        assertAll(
            () -> assertThat(diskMap.getDelegate()).containsAllEntriesOf(map),
            () -> assertFileContains(map)
        );
    }

    private void assertFileContains(Map<String, String> expectedContent) {
        try (Scanner scanner = new Scanner(path.toFile())) {
            var strings = new StringBuilder();
            while (scanner.hasNext()) {
                strings.append(scanner.nextLine()).append("\n");
            }
            Map<String, String> deserializedMap = SerializationUtils.deserializeString(strings.toString());

            for (Map.Entry<String, String> entry : expectedContent.entrySet()) {
                assertThat(deserializedMap).contains(entry);
            }
        } catch (Exception e) {
            fail("Failed to read file content", e);
        }
    }

    private void assertFileIsEmpty() {
        try {
            byte[] bytes = Files.readAllBytes(path);
            assertThat(bytes).isEmpty();
        } catch (Exception e) {
            fail("Failed to read file content", e);
        }
    }

}
