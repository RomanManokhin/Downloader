package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderImplTest {


    String fileUrl = "https://ruv.hotmo.org/get/music/20191123/Mjevl_-_KHolodok_67381798.mp3";
    String fileName = "Mjevl_-_KHolodok_67381798.mp3";
    int downloadSpeed = 512000;
    String folderNameToDownload  = "src/main/resources/download/";

    Downloader downloader = new DownloaderImpl(fileUrl, fileName, downloadSpeed, folderNameToDownload);

    @Test
    void startThread() {

        assertTrue(downloader.startThread());

    }
}