package io.whitepaper.coder.talks;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.openqa.selenium.OutputType;

import java.io.*;


public class Main {

    public static void main(String... args) throws IOException {
        System.out.println();
        JBrowserDriver driver = new JBrowserDriver(Settings.builder()
                .timezone(Timezone.ASIA_SHANGHAI)
                .userAgent(UserAgent.TOR)
                .build());

        driver.get("http://www.baidu.com/");

        driver.getStatusCode();

        System.out.println(driver.getPageSource());
        byte[] bytes = driver.getScreenshotAs(OutputType.BYTES);

        String file = "/Users/george/baidu.png";
        //Files.createFile(Paths.get(file));
        FileOutputStream stream = new FileOutputStream(new File(file));
        stream.write(bytes);
        stream.close();
        driver.quit();
    }
}
