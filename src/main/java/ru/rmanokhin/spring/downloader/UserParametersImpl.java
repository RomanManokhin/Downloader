package ru.rmanokhin.spring.downloader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * класс для получения данных от пользователя
 */
@Component
public class UserParametersImpl implements UserParameters {
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

    /**
     * метод для получения количества потоков скачивания от пользователя
     */
    @Override
    public int takeThreads() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        int threads = 0;

        try {
            String temp = bufferedReader.readLine();
            threads = Integer.parseInt(temp);
        } catch (IOException e) {

        }

        return threads;
    }

    /**
     * метод для получения места хранения файла из которого необходимо брать ссылки для скачивания
     */
    @Override
    public String takePathFile() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        String temp = null;
        try {
            temp = bufferedReader.readLine();
            boolean checkExtension = temp.endsWith(".txt");

            File fileNotEmpty = new File(temp);

            if (checkExtension && fileNotEmpty.length() != 0) {
                return temp;
            } else {
                return null;
            }

        } catch (Exception ignored) {

        }
        return temp;
    }

    /**
     * метод для получения скорости скачивания файлов
     */
    @Override
    public int downloadSpeed() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        int downloadSpeed = 0;

        try {
            downloadSpeed = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return downloadSpeed * 1024;
    }


    /**
     * метод для получения пути сохранения файлов от пользователя
     */
    @Override
    public String pathDownload() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        String pathToDownload = null;
        boolean checkExtensionFirst = false;
        boolean checkExtensionSecond = false;
        try {
            pathToDownload = bufferedReader.readLine();
            checkExtensionFirst = pathToDownload.startsWith("C:\\");
            checkExtensionSecond = pathToDownload.startsWith("src\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pathToDownload != null;
        File folder = new File(pathToDownload);
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (checkExtensionFirst || checkExtensionSecond) {
            return pathToDownload;
        } else {
            return null;
        }

    }
}
