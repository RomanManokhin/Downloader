package ru.rmanokhin.spring.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rmanokhin.spring.downloader.BootPreparation;
import ru.rmanokhin.spring.downloader.MultiThreadedDownloader;
import ru.rmanokhin.spring.menu.MainMenu;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The class that initializes the program
 */
@Component
public class StartProgramImpl implements StartProgram {

    private final MainMenu mainMenu;
    private final BootPreparation bootPreparation;
    private final MultiThreadedDownloader multiThreadedDownloader;

    @Autowired
    public StartProgramImpl(MainMenu mainMenu, BootPreparation bootPreparation, MultiThreadedDownloader multiThreadedDownloader) {
        this.mainMenu = mainMenu;
        this.bootPreparation = bootPreparation;
        this.multiThreadedDownloader = multiThreadedDownloader;
    }

    /**
     * Method for initializing, getting data and running the program
     */
    public boolean start() {

        String pathFile = mainMenu.menuTakePathFile();
        int countThreads = mainMenu.menuCountThreads();
        int downloadSpeed = mainMenu.menuDownloadSpeed();
        String folderForDownload = mainMenu.menuPathDownload();

        List<String> urls = bootPreparation.parsingFileForUrls(pathFile);
        List<String> fileNames = bootPreparation.parsingListUrlsForNames(urls);

        multiThreadedDownloader.startDownloading(countThreads, urls.size(), urls, fileNames, downloadSpeed, folderForDownload);

        return true;
    }
}
