package ru.rmanokhin.spring.downloader;

import java.io.IOException;

public interface UserParameters {

    int takeThreads() throws IOException;

    String takePathFile();

    int downloadSpeed();

    String pathDownload();

}
