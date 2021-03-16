package ru.rmanokhin.spring.downloader;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс для загрузки в многопоточном режиме
 */
@Component
public class MultiThreadedDownloaderImpl implements MultiThreadedDownloader {

    /**
     * Method that starts the process of downloading files in multithreaded mode
     *
     * @return true
     */
    ExecutorService executorService;

    @Override
    public boolean startDownloading(int countThreads, int countUrls, List<String> urls, List<String> fileNames,
                                    int downloadSpeed, String folderForDownload) {


//        ExecutorService executorService = Executors.newFixedThreadPool(countThreads);
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            for (int i = 0; i < countUrls; i++) {
//                executorService.submit(DownloaderImpl.builder()
//                        .downloadSpeed(downloadSpeed)
//                        .fileName(fileNames.get(i))
//                        .fileUrl(urls.get(i))
//                        .pathToFolder(folderForDownload)
//                        .build());
//            }
//        }, executorService);


//        try {
//            future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

//        executorService.shutdown();
//        List<Future<?>> futures = new ArrayList<>();
//        ExecutorService executorService = Executors.newFixedThreadPool(countThreads);
//        for (int i = 0; i < countUrls; i++) {
//            Future<?> f = executorService.submit(DownloaderImpl.builder()
//                    .downloadSpeed(downloadSpeed)
//                    .fileName(fileNames.get(i))
//                    .fileUrl(urls.get(i))
//                    .pathToFolder(folderForDownload)
//                    .build());
//            futures.add(f);
//        }
//        for(Future<?> future : futures) {
//            try {
//                future.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

//        boolean allDone = true;
//        for(Future<?> future : futures){
//            allDone &= future.isDone();
//        }

//            executorService.submit(DownloaderImpl.builder()
//                    .downloadSpeed(downloadSpeed)
//                    .fileName(fileNames.get(i))
//                    .fileUrl(urls.get(i))
//                    .pathToFolder(folderForDownload)
//                    .build());

//
//        executorService.shutdown();
//
//        return allDone;


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

        return executorService.isShutdown();

    }


}
