package ru.rmanokhin.spring.downloader;

import java.io.FileNotFoundException;
import java.util.List;

public interface BootPreparation {

    List<String> parsingFileForUrls(String pathFile) throws FileNotFoundException;

    List<String> parsingListUrlsForNames(List<String> urls);

}
