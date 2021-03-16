package ru.rmanokhin.spring.initiator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rmanokhin.spring.downloader.BootPreparation;
import ru.rmanokhin.spring.downloader.MultiThreadedDownloader;
import ru.rmanokhin.spring.menu.MainMenu;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The class that initializes the program
 */
@Component
public class InitializationImpl implements Initialization {

    private final MainMenu mainMenu;
    private final BootPreparation bootPreparation;
    private final MultiThreadedDownloader multiThreadedDownloader;

    @Autowired
    public InitializationImpl(MainMenu mainMenu, BootPreparation bootPreparation, MultiThreadedDownloader multiThreadedDownloader) {
        this.mainMenu = mainMenu;
        this.bootPreparation = bootPreparation;
        this.multiThreadedDownloader = multiThreadedDownloader;
    }

    /**
     * Method for initializing, getting data and running the program
     */
    public void start() {

        String pathFile = mainMenu.menuTakePathFile();
        int countThreads = mainMenu.menuCountThreads();
        int downloadSpeed = mainMenu.menuDownloadSpeed();
        String folderForDownload = mainMenu.menuPathDownload();

        List<String> urls = null;
        try {
            urls = bootPreparation.parsingFileForUrls(pathFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> fileNames = bootPreparation.parsingListUrlsForNames(urls);

        multiThreadedDownloader.startDownloading(countThreads, urls.size(), urls, fileNames, downloadSpeed, folderForDownload);

    }


}
