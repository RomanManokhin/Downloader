package ru.rmanokhin.spring.downloader;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * Класс который производит загрузку файла
 */
public class DownloaderImpl implements Downloader {

    /**
     * поле для ссылки для скачивание файла
     */
    private final String fileUrl;

    /**
     * поле для имени файла
     */
    private final String fileName;

    /**
     * поле для скорости загрузки
     */
    private final int downloadSpeed;


    /**
     * переменная для пути сохранения файлов
     */
    private final String folderNameToDownload;

    InputStream inputStream;
    FileOutputStream fileOutputStream;
    ByteArrayInputStream byteArrayInputStream;
    ByteArrayOutputStream byteArrayOutputStream;

    /**
     * буфер для считывания из потока
     */
    byte[] bufferForDownload;

    /**
     * переменная для счета прочитанных байт из потока за итерацию
     */
    int numberOfBytesRead;

    /**
     * переменная для буфера части файла
     */
    byte[] bufferFile;

    /**
     * передаём поток в байтовый массив
     */
    byte[] bytesToStream;


    public DownloaderImpl(String fileUrl, String fileName, int downloadSpeed, String folderNameToDownload) {
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.downloadSpeed = downloadSpeed;
        this.folderNameToDownload = folderNameToDownload;
    }

    @Override
    public void run() {
        startThread();
    }

    @Override
    public boolean startThread() {
        System.out.println(Thread.currentThread() + " started");

        try {
            inputStream = new URL(fileUrl).openStream();
            bytesToStream = IOUtils.toByteArray(inputStream);
            fileOutputStream = new FileOutputStream(folderNameToDownload + "\\" + fileName, true);
            bufferForDownload = new byte[downloadSpeed];

            byteArrayInputStream = new ByteArrayInputStream(bytesToStream);
            byteArrayOutputStream = new ByteArrayOutputStream();

            while ((numberOfBytesRead = byteArrayInputStream.read(bufferForDownload, 0, downloadSpeed)) != -1) {

                byteArrayOutputStream.write(bufferForDownload, 0, numberOfBytesRead);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            bufferFile = byteArrayOutputStream.toByteArray();

            fileOutputStream.write(bufferFile);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread() + " finished");
        return Thread.currentThread().isAlive();
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




