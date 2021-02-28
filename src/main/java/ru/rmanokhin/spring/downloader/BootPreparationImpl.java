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
 * Класс для парсинга файла и получения необходимы данных из него
 */
@Component
public class BootPreparationImpl implements BootPreparation {

    /**
     * метод для парсинга файла и получения ссылок для скачивания
     */
    @Override
    public List<String> parsingFileForUrls(String pathFile) {
        List<String> urlsFromFile = null;
        File file = new File(pathFile);
        if(!file.isFile()){
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
     * метод для получения имен файлов из листа с ссылками
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
