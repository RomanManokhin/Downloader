package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderImplTest {


    String fileUrl = "http://speedtest.ftp.otenet.gr/files/test1Mb.db";
    String fileName = "test1Mb.db";
    int downloadSpeed = 512000;
    String folderNameToDownload = "src/main/resources/downloads/";
    int actual = 1_048_576;
    DownloaderImpl downloader = new DownloaderImpl(fileUrl, fileName, downloadSpeed, folderNameToDownload);


    @Test
    void startThread() {

        DownloaderImpl.builder()
                .downloadSpeed(downloadSpeed)
                .fileName(fileName)
                .fileUrl(fileUrl)
                .pathToFolder(folderNameToDownload)
                .build();
        downloader.startThread();

        File file = new File("src/main/resources/downloads/test1Mb.db");

        assertEquals(file.length(), actual);

        file.delete();

    }
}