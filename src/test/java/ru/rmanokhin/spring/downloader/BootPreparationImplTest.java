package ru.rmanokhin.spring.downloader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BootPreparationImplTest {

    private static BootPreparationImpl bootPreparation;

    Throwable thrown;

    @Autowired
    public void testSetStart(BootPreparationImpl bootPreparation) {
        BootPreparationImplTest.bootPreparation = bootPreparation;
    }

    List<String> testList = Arrays.asList(
            "https://ruv.hotmo.org/get/music/20191123/Mjevl_-_KHolodok_67381798.mp3"
            , "https://ruv.hotmo.org/get/music/20210108/Ruki_Vverkh_Oksana_Pochepa_-_Tolko_dlya_tebya_72239067.mp3"
            , "https://ruv.hotmo.org/get/music/20190410/Raim_-_Dvigatsya_63406775.mp3"
            , "https://ruv.hotmo.org/get/music/20190915/SAINt_JHN_Imanbek_-_Roses_66582659.mp3"
            , "https://ruv.hotmo.org/get/music/20200207/Artik_Asti_-_Vse_mimo_68289046.mp3");

    @Test
    void testParsingFileForUrls() throws FileNotFoundException {

        String actualPathToFile = "src/main/resources/info/data.txt";
        String wrongPathToFile = "src/main/resources/infox/data.txt";

        List<String> actualList = bootPreparation.parsingFileForUrls(actualPathToFile);

        assertEquals(testList, actualList);

        thrown = assertThrows(FileNotFoundException.class, () -> bootPreparation.parsingFileForUrls(wrongPathToFile), "File no found");

        assertNotNull(thrown);

    }

    @Test
    void testParsingListUrlsForNames() {

        List<String> expectedList1 = Arrays.asList("Mjevl_-_KHolodok_67381798.mp3"
                , "Ruki_Vverkh_Oksana_Pochepa_-_Tolko_dlya_tebya_72239067.mp3"
                , "Raim_-_Dvigatsya_63406775.mp3"
                , "SAINt_JHN_Imanbek_-_Roses_66582659.mp3"
                , "Artik_Asti_-_Vse_mimo_68289046.mp3"
        );

        List<String> resultList1 = bootPreparation.parsingListUrlsForNames(testList);
        assertEquals(expectedList1, resultList1);

        List<String> expectedList2 = new ArrayList<>();

        thrown = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> bootPreparation.parsingListUrlsForNames(expectedList2));

        assertNotNull(thrown);

    }
}