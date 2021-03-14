package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MultiThreadedDownloaderImplTest {

    private final MultiThreadedDownloader multiThreadedDownloader;

    @Autowired
    MultiThreadedDownloaderImplTest(MultiThreadedDownloader multiThreadedDownloader) {
        this.multiThreadedDownloader = multiThreadedDownloader;
    }

    int testCountThreads = 5;
    int testCountUrls = 5;
    int testDownloadSpeed = 500;
    String testFolderForDownload = "src/main/resources/downloads/";

    List<String> testFileNames = Arrays.asList("test1"
            , "test2"
            , "test3"
            , "test4"
            , "test5");

    List<String> testUrlList = Arrays.asList(
            "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db");


    @Test
    void startDownloading() {

        assertTrue(multiThreadedDownloader.startDownloading(testCountThreads, testCountUrls, testUrlList, testFileNames, testDownloadSpeed, testFolderForDownload));

    }
}