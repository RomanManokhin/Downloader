package ru.rmanokhin.spring.downloader;

import java.util.List;

public interface MultiThreadedDownloader {

    void startDownloading(int countThreads, int countUrls, List<String> urls,
                          List<String> fileNames, int downloadSpeed, String folderForDownload);

}
