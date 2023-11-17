package edu.hw6.task1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Path path;
    private Map<String, String> delegate;

    protected DiskMap(Map<String, String> delegate, Path path) {
        this.delegate = delegate;
        this.path = path;
        loadFromDisk();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return delegate.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var putResult = delegate.put(key, value);
        flushToDisk();
        return putResult;
    }

    @Override
    public String remove(Object key) {
        var remoteResult = delegate.remove(key);
        flushToDisk();
        return remoteResult;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        delegate.putAll(m);
        flushToDisk();
    }

    @Override
    public void clear() {
        delegate.clear();
        flushToDisk();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return delegate.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return delegate.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return delegate.entrySet();
    }

    public void flushToDisk() {
        try
            (
                PrintWriter printWriter = new PrintWriter(path.toFile())
            ) {
            if (delegate.isEmpty()) {
                LOGGER.info("Truncated \"{}\" as delegating map is empty", path);
                return;
            }
            printWriter.print("");
            printWriter.flush();
            var serializedMap = SerializationUtils.serializeMap(delegate);
            printWriter.print(serializedMap);
            printWriter.flush();
            LOGGER.info("Flushed {} symbols to \"{}\"", serializedMap.length(), path);
        } catch (FileNotFoundException e) {
            LOGGER.error("Caught an exception during flushing DiskMap to the disk", e);
            throw new FlushToDiskException(e);
        }
    }

    public void loadFromDisk() {
        try
            (
                Scanner scanner = new Scanner(path.toFile())
            ) {
            if (!scanner.hasNext()) {
                LOGGER.info("\"{}\" contains no data", path);
                delegate.clear();
                return;
            }
            delegate.clear();
            var strings = new StringBuilder();
            while (scanner.hasNext()) {
                strings.append(scanner.nextLine());
            }
            delegate = SerializationUtils.deserializeString(strings.toString());
            LOGGER.info("Loaded {} entry from \"{}\"", delegate.size(), path);
        } catch (FileNotFoundException e) {
            LOGGER.error("Caught an exception during loading DiskMap from the disk", e);
            throw new LoadFromDiskException(e);
        }
    }

    public Map<String, String> getDelegate() {
        return delegate;
    }
}
