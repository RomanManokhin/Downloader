package ru.rmanokhin.spring.downloader;

import java.util.List;

public interface BootPreparation {

    List<String> parsingFileForUrls(String pathFile);

    List<String> parsingListUrlsForNames(List<String> urls);

}
