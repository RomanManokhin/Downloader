package ru.rmanokhin.spring.downloader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for handling user input
 */
@Component
public class UserParametersImpl implements UserParameters {
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

    /**
     * Method for getting the number of download streams from a user
     *
     * @return count threads
     */
    @Override
    public int takeThreads() throws IOException {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        return Integer.parseInt(bufferedReader.readLine());
    }

    /**
     * Method for getting the path to a file with links
     */
    @Override
    public String takePathFile() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        String pathFile = null;
        try {
            pathFile = bufferedReader.readLine();
            boolean checkExtension = pathFile.endsWith(".txt");

            File fileNotEmpty = new File(pathFile);

            if (checkExtension && fileNotEmpty.length() != 0) {
                return pathFile;
            } else {
                return null;
            }

        } catch (Exception ignored) {

        }
        return pathFile;
    }

    /**
     * Method for getting the download speed of files
     * @return download speed > 0
     */
    @Override
    public int downloadSpeed() throws IOException {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        return Integer.parseInt(bufferedReader.readLine()) * 1024;
    }


    /**
     * Method for getting the path to save files from the user
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
