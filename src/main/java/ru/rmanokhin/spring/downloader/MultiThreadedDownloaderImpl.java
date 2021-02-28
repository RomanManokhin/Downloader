package ru.rmanokhin.spring.downloader;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * класс для загрузки в многопоточном режиме
 */
@Component
public class MultiThreadedDownloaderImpl implements MultiThreadedDownloader {

    /**
     * метод запускающий процесс скачивания файлов в многопоточном режиме
     */
    @Override
    public boolean startDownloading(int countThreads, int countUrls, List<String> urls, List<String> fileNames,
                                    int downloadSpeed, String folderForDownload) {

        ExecutorService executorService = Executors.newFixedThreadPool(countThreads);

        for (int i = 0; i < countUrls; i++) {
            executorService.submit(DownloaderImpl.builder()
                    .downloadSpeed(downloadSpeed)
                    .fileName(fileNames.get(i))
                    .fileUrl(urls.get(i))
                    .pathToFolder(folderForDownload)
                    .build());
        }

        executorService.shutdown();
        return executorService.isShutdown();
    }


}
