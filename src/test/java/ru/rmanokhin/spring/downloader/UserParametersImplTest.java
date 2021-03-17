package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserParametersImplTest {

    private static UserParameters userParameters;

    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    ByteArrayInputStream byteArrayInputStream;

    @Autowired
    public void setStart(UserParameters userParameters) {
        UserParametersImplTest.userParameters = userParameters;
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
    void testTakeThreadsWithText() {
        String excepted = "a";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        Throwable thrown = assertThrows(NumberFormatException.class, () -> {
            userParameters.takeThreads();
        });

        assertNotNull(thrown);

    }

    @Test
    void testTakeThreadsActual() throws IOException {
        String excepted = "5";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = userParameters.takeThreads();

        assertEquals(5, actual);

    }

    @Test
    void testTakeThreadsWrongCount() throws IOException {
        String excepted = "0";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = userParameters.takeThreads();

        assertEquals(0, actual);

    }

    @Test
    void testTakePathFileActual() {
        String excepted = "src/main/resources/testFiles/testFile.txt";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = userParameters.takePathFile();

        assertEquals(excepted, actual);

    }

    @Test
    void testTakePathFileWithEmptyFile() {
        String excepted = "src/main/resources/testFiles/test_empty_data.txt";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = userParameters.takePathFile();

        assertNull(actual);

    }

    @Test
    void testTakePathFileWithWrongExpansionFile() {
        String excepted = "src/main/resources/testFiles/testFileWithOutExpansion";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = userParameters.takePathFile();

        assertNull(actual);

    }

    @Test
    void testDownloadSpeedWithText() {
        String excepted = "a";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        Throwable thrown = assertThrows(NumberFormatException.class, () -> {
            userParameters.downloadSpeed();
        });

        assertNotNull(thrown);

    }

    @Test
    void testDownloadSpeedWithZeroSpeed() throws IOException {
        String excepted = "0";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = userParameters.downloadSpeed();

        assertEquals(0, actual);

    }

    @Test
    void testDownloadSpeedActual() throws IOException {
        String excepted = "500";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        int actual = userParameters.downloadSpeed();

        assertEquals(512000, actual);

    }

    @Test
    void testPathDownloadActual() {
        String excepted = "C:\\path";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = userParameters.pathDownload();

        assertEquals(excepted, actual);

    }

    @Test
    void testPathDownloadWithWrongPathToDownload() {
        String excepted = "path";
        byteArrayInputStream = new ByteArrayInputStream(excepted.getBytes());
        System.setIn(byteArrayInputStream);

        String actual = userParameters.pathDownload();

        assertNull(actual);

    }
}