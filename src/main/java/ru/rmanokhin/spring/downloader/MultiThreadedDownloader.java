package ru.rmanokhin.spring.downloader;

import java.util.List;

public interface MultiThreadedDownloader {

    boolean startDownloading(int countThreads, int countUrls, List<String> urls,
                             List<String> fileNames, int downloadSpeed, String folderForDownload);

}
