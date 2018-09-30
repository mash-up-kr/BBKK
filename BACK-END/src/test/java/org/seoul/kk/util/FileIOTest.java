package org.seoul.kk.util;

import com.amazonaws.util.Base64;
import org.junit.jupiter.api.Test;
import org.seoul.kk.SpringTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileIOTest extends SpringTestSupport {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void writeFileAfterReadFileTest() throws IOException {
        File testFile = resourceLoader.getResource("classpath:/s3test-image.png").getFile();
        byte[] binaryDate = Files.readAllBytes(testFile.toPath());

        byte[] encodedData = Base64.encode(binaryDate);
        byte[] decodedData = Base64.decode(encodedData);

        File writeFile = new File("test.png");

        FileOutputStream fileOutputStream = new FileOutputStream(writeFile);
        fileOutputStream.write(decodedData);

        assertTrue(writeFile.exists());
        assertTrue(writeFile.delete());
    }

}
