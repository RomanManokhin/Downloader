package ru.rmanokhin.spring.downloader;

import org.springframework.stereotype.Component;

import java.io.File;
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

    /**
     * Method for getting links from a file
     */
    @Override
    public List<String> parsingFileForUrls(String pathFile) {
        List<String> urlsFromFile = null;
        File file = new File(pathFile);
        if (!file.isFile()) {
            throw new ArrayIndexOutOfBoundsException("File not found");
        }
        try {
            urlsFromFile = Files.readAllLines(Paths.get(pathFile));
        } catch (IOException e) {
            e.printStackTrace();
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
            throw new ArrayIndexOutOfBoundsException("Лист пуст");
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
