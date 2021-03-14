package ru.rmanokhin.spring.downloader;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * Class for starting the download stream
 */
public class DownloaderImpl implements Downloader {

    /**
     * Download link
     */
    private final String fileUrlToDownload;

    /**
     * Filename by reference
     */
    private final String fileNameFromLink;

    /**
     * Download speed
     */
    private final int downloadSpeed;

    /**
     * File save path
     */
    private final String folderNameToDownload;

    ByteArrayOutputStream byteArrayOutputStream;
    InputStream inputStream;

    /**
     * Buffer to read from stream
     */
    byte[] bufferForDownload;

    /**
     * Variable for counting bytes read from the stream per iteration
     */
    int numberOfBytesRead;

    /**
     * File part buffer
     */
    byte[] bufferFile;

    public DownloaderImpl(String fileUrlToDownload, String fileNameFromLink, int downloadSpeed, String folderNameToDownload) {
        this.fileUrlToDownload = fileUrlToDownload;
        this.fileNameFromLink = fileNameFromLink;
        this.downloadSpeed = downloadSpeed;
        this.folderNameToDownload = folderNameToDownload;
    }

    @Override
    public void run() {
        startThread();
    }

    /**
     * Method for starting a thread
     * Removed from Run () for testing
     */
    @Override
    public void startThread() {

        try (FileOutputStream fileOutputStream = new FileOutputStream(folderNameToDownload + "\\" + fileNameFromLink)) {

            bufferForDownload = new byte[downloadSpeed];
            byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = new URL(fileUrlToDownload).openStream();

            while ((numberOfBytesRead = IOUtils.read(inputStream, bufferForDownload, 0 , downloadSpeed)) != 0) {

                byteArrayOutputStream.write(bufferForDownload, 0, numberOfBytesRead);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            bufferFile = byteArrayOutputStream.toByteArray();

            fileOutputStream.write(bufferFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DownloaderBuilder builder() {
        return new DownloaderBuilder();
    }

    public static class DownloaderBuilder {
        private String fileUrl;
        private String fileName;
        private int downloadSpeed;
        private String folderNameToDownload;

        public DownloaderBuilder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public DownloaderBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public DownloaderBuilder downloadSpeed(int downloadSpeed) {
            this.downloadSpeed = downloadSpeed;
            return this;
        }

        public DownloaderBuilder pathToFolder(String folderNameToDownload) {
            this.folderNameToDownload = folderNameToDownload;
            return this;
        }

        public DownloaderImpl build() {
            return new DownloaderImpl(fileUrl, fileName, downloadSpeed, folderNameToDownload);
        }
    }
}




