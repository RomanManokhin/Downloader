package ru.rmanokhin.spring.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.rmanokhin.spring.downloader.UserParameters;

import java.io.IOException;

/**
 * Menu class for working with a user
 */
@Component
public class MainMenuImpl implements MainMenu {

    private static final Logger logger = LogManager.getLogger();

    private final UserParameters userParameters;

    public MainMenuImpl(UserParameters userParameters) {
        this.userParameters = userParameters;
    }

    /**
     * Method for getting the number of download streams
     *
     * @return count threads > 0
     */
    @Override
    public int menuCountThreads() {

        int threads = 0;

        do {
            logger.info("Enter the number of streams to download: ");
            try {
                threads = userParameters.takeThreads();
                if (threads <= 0) {
                    logger.error("The number should be > 0");
                }
            } catch (IOException | NumberFormatException e) {
                logger.error("Not a number entered");
            }
        } while (threads <= 0);
        return threads;
    }

    /**
     * Method for getting the path to a file with links
     */
    @Override
    public String menuTakePathFile() {

        String pathFile = null;

        do {
            logger.info("Can use that - src/main/resources/info/data.txt");
            logger.info("Enter file path: ");
            try {
                pathFile = userParameters.takePathFile();
                if (pathFile == null) {
                    logger.error("File extension is not .txt or wrong path\n");
                }
            } catch (Exception ignored) {

            }

        } while (pathFile == null);
        return pathFile;
    }

    /**
     * Method for getting download speed
     *
     * @return download speed > 0
     */
    @Override
    public int menuDownloadSpeed() {

        int downloadSpeed = 0;

        do {
            logger.info("Enter download speed in kb: ");
            try {
                downloadSpeed = userParameters.downloadSpeed();
                if (downloadSpeed <= 0) {
                    logger.info("Speed must be greater than 0kb");
                }
            } catch (IOException | NumberFormatException e) {
                logger.error("Not a number entered");
            }
        } while (downloadSpeed <= 0);
        return downloadSpeed;
    }

    /**
     * Method for getting the path for saving files
     */
    @Override
    public String menuPathDownload() {

        String pathDownload;

        do {
            logger.info("Enter path to save files: ");
            logger.info("Example: C:\\folderName");
            logger.info("Or use that - src\\main\\resources\\downloads");

            pathDownload = userParameters.pathDownload();

            if (pathDownload == null) {
                logger.error("Wrong way to download\n");
            }

        } while (pathDownload == null);

        return pathDownload;
    }
}
