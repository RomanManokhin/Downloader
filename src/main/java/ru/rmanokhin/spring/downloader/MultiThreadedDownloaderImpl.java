package ru.rmanokhin.spring.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Класс для загрузки в многопоточном режиме
 */
@Component
public class MultiThreadedDownloaderImpl implements MultiThreadedDownloader {

    /**
     * Method that starts the process of downloading files in multithreaded mode
     */
    ExecutorService executorService;

    private static final Logger logger = LogManager.getLogger(MultiThreadedDownloaderImpl.class.getName());

    @Override
    public void startDownloading(int countThreads, int countUrls, List<String> urls, List<String> fileNames,
                                 int downloadSpeed, String folderForDownload) {

        logger.info("Start Downloading");
        executorService = Executors.newFixedThreadPool(countThreads);

        for (int i = 0; i < countUrls; i++) {
            executorService.submit(DownloaderImpl.builder()
                    .downloadSpeed(downloadSpeed)
                    .fileName(fileNames.get(i))
                    .fileUrl(urls.get(i))
                    .pathToFolder(folderForDownload)
                    .build());
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Files downloaded");


    }
}
