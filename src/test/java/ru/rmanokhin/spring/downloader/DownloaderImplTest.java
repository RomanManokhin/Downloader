package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(4)
class DownloaderImplTest {

    String fileUrl = "http://speedtest.ftp.otenet.gr/files/test1Mb.db";
    String fileName = "test1Mb.db";
    int downloadSpeed = 524_288;
    String folderNameToDownload = "src/main/resources/downloads_for_test_files/";
    int actual = 1_048_576;

    Downloader downloader = DownloaderImpl.builder()
            .downloadSpeed(downloadSpeed)
            .fileName(fileName)
            .fileUrl(fileUrl)
            .pathToFolder(folderNameToDownload)
            .build();

    File file = new File("src/main/resources/downloads_for_test_files/test1Mb.db");

    @AfterEach
    void deleteTestFile() {
        file.delete();
    }

    @Test
    void startThread() {

        downloader.startThread();

        assertEquals(file.length(), actual);

    }
}