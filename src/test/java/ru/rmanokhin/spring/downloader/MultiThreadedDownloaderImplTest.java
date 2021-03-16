package ru.rmanokhin.spring.downloader;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.concurrent.TimeUnit.*;

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
    int testDownloadSpeed = 500;
    String testFolderForDownload = "src/main/resources/downloads_for_test_files/";

    List<String> testFileNames = Arrays.asList("test1"
            , "test2"
            , "test3"
            , "test4"
            , "test5");

    List<String> testUrlList = Arrays.asList(
            "https://ruv.hotmo.org/get/music/20191123/Mjevl_-_KHolodok_67381798.mp3",
    "https://ruv.hotmo.org/get/music/20210108/Ruki_Vverkh_Oksana_Pochepa_-_Tolko_dlya_tebya_72239067.mp3",
    "https://ruv.hotmo.org/get/music/20190410/Raim_-_Dvigatsya_63406775.mp3",
    "https://ruv.hotmo.org/get/music/20190915/SAINt_JHN_Imanbek_-_Roses_66582659.mp3",
    "https://ruv.hotmo.org/get/music/20200207/Artik_Asti_-_Vse_mimo_68289046.mp3");
//            "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
//            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
//            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
//            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db"
//            , "http://speedtest.ftp.otenet.gr/files/test1Mb.db");

    int actualSize = 1_048_576;


    @Test

    void startDownloading() throws InterruptedException {

        multiThreadedDownloader.startDownloading(testCountThreads, testCountUrls, testUrlList, testFileNames, testDownloadSpeed, testFolderForDownload);

        //        Thread.sleep(15000);

            File file1 = new File("src/main/resources/downloads_for_test_files/test1");
            File file2 = new File("src/main/resources/downloads_for_test_files/test2");
            File file3 = new File("src/main/resources/downloads_for_test_files/test3");
            File file4 = new File("src/main/resources/downloads_for_test_files/test4");
            File file5 = new File("src/main/resources/downloads_for_test_files/test5");


//            assertEquals(file1.length(), actualSize);
//            assertEquals(file2.length(), actualSize);
//            assertEquals(file3.length(), actualSize);
//            assertEquals(file4.length(), actualSize);
//            assertEquals(file5.length(), actualSize);


    }
}