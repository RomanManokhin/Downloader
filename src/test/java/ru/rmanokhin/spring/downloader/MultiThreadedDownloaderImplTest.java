package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MultiThreadedDownloaderImplTest {

    private final MultiThreadedDownloader multiThreadedDownloader;

    @Autowired
    MultiThreadedDownloaderImplTest(MultiThreadedDownloader multiThreadedDownloader) {
        this.multiThreadedDownloader = multiThreadedDownloader;
    }

    int testCountThreads = 5;
    int testCountUrls = 5;
    int testDownloadSpeed = 524_288;
    String testFolderForDownload = "src/main/resources/downloads_for_test_files/";

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

    int actualSize = 1_048_576;

    CountDownLatch latch;

    File file1;
    File file2;
    File file3;
    File file4;
    File file5;

    @AfterEach
    void deleteTestFile() {
        file1.delete();
        file2.delete();
        file3.delete();
        file4.delete();
        file5.delete();
    }

    @Test
    @Timeout(5)
    void startDownloading() throws InterruptedException {

        latch = new CountDownLatch(testCountUrls);

        new Thread(() -> {
            multiThreadedDownloader.startDownloading(testCountThreads, testCountUrls, testUrlList, testFileNames, testDownloadSpeed, testFolderForDownload);
            latch.countDown();
        }).start();
        latch.await(4, SECONDS);

        file1 = new File("src/main/resources/downloads_for_test_files/test1");
        file2 = new File("src/main/resources/downloads_for_test_files/test2");
        file3 = new File("src/main/resources/downloads_for_test_files/test3");
        file4 = new File("src/main/resources/downloads_for_test_files/test4");
        file5 = new File("src/main/resources/downloads_for_test_files/test5");


        assertEquals(file1.length(), actualSize);
        assertEquals(file2.length(), actualSize);
        assertEquals(file3.length(), actualSize);
        assertEquals(file4.length(), actualSize);
        assertEquals(file5.length(), actualSize);


    }
}