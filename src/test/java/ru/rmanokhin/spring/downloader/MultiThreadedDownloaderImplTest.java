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

    List<String> testFileNames = Arrays.asList("Mjevl_-_KHolodok_67381798.mp3"
            , "Ruki_Vverkh_Oksana_Pochepa_-_Tolko_dlya_tebya_72239067.mp3"
            , "Raim_-_Dvigatsya_63406775.mp3"
            , "SAINt_JHN_Imanbek_-_Roses_66582659.mp3"
            , "Artik_Asti_-_Vse_mimo_68289046.mp3");

    List<String> testUrlList = Arrays.asList(
            "https://ruv.hotmo.org/get/music/20191123/Mjevl_-_KHolodok_67381798.mp3"
            , "https://ruv.hotmo.org/get/music/20210108/Ruki_Vverkh_Oksana_Pochepa_-_Tolko_dlya_tebya_72239067.mp3"
            , "https://ruv.hotmo.org/get/music/20190410/Raim_-_Dvigatsya_63406775.mp3"
            , "https://ruv.hotmo.org/get/music/20190915/SAINt_JHN_Imanbek_-_Roses_66582659.mp3"
            , "https://ruv.hotmo.org/get/music/20200207/Artik_Asti_-_Vse_mimo_68289046.mp3");


    @Test
    void startDownloading() {

        assertTrue(multiThreadedDownloader.startDownloading(testCountThreads, testCountUrls, testUrlList, testFileNames, testDownloadSpeed, testFolderForDownload));

    }
}