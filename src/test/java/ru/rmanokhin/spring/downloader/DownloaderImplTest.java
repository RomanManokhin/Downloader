package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderImplTest {


    String fileUrl = "http://speedtest.ftp.otenet.gr/files/test1Mb.db";
    String fileName = "test1Mb.db";
    int downloadSpeed = 512000;
    String folderNameToDownload  = "src/main/resources/download/";

    Downloader downloader = new DownloaderImpl(fileUrl, fileName, downloadSpeed, folderNameToDownload);

    @Test
    void startThread() {

        assertTrue(downloader.startThread());

    }
}