package ru.rmanokhin.spring.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for parsing a file with links and getting data from it
 */
@Component
public class BootPreparationImpl implements BootPreparation {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Method for getting links from a file
     */
    @Override
    public List<String> parsingFileForUrls(String pathFile) throws FileNotFoundException {
        List<String> urlsFromFile = null;
        File file = new File(pathFile);

        if (!file.isFile()) {
            logger.error("FileNotFoundException");
            throw new FileNotFoundException();
        }

        try {
            urlsFromFile = Files.readAllLines(Paths.get(pathFile));
        } catch (IOException ignored) {

        }
        return urlsFromFile;
    }

    /**
     * Method for getting filenames from links
     */
    @Override
    public List<String> parsingListUrlsForNames(List<String> urls) {
        List<String> namesForFiles = new ArrayList<>();

        if (urls.isEmpty()) {
            logger.error("List is empty");
            throw new ArrayIndexOutOfBoundsException("List is empty");
        } else {
            for (String s : urls) {
                try {
                    namesForFiles.add(Paths.get(new URI(s).getPath()).getFileName().toString());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        return namesForFiles;
    }

}
