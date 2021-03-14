package ru.rmanokhin.spring.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainMenuImplTest {

    private static MainMenu mainMenu;

    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    ByteArrayInputStream byteArrayInputStream;

    @Autowired
    public void setStart(MainMenu mainMenu) {
        MainMenuImplTest.mainMenu = mainMenu;
    }

    @BeforeEach
    void initTests() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    @AfterEach
    void endTests() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
    }

    @Test
    void testTakeThreads() {
        String excepted = "5";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = mainMenu.menuCountThreads();

        assertEquals(5, actual);

    }

    @Test
    void testTakePathFile() {
        String excepted = "src/main/resources/info/testFile.txt";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = mainMenu.menuTakePathFile();

        assertEquals(excepted, actual);

    }

    @Test
    void testDownloadSpeed() {
        String excepted = "500";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = mainMenu.menuDownloadSpeed();

        assertEquals(512000, actual);
    }

    @Test
    void testPathDownload() throws IOException {
        String excepted = "C:\\path";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = mainMenu.menuPathDownload();

        assertEquals(excepted, actual);
    }
}