package com.lumatest.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;

public final class BaseUtils {
    private static ChromeOptions chromeOptions;

    static boolean isCIRun() {
        return System.getenv("CI_RUN") != null;
    }

    static {
        chromeOptions= new ChromeOptions();

        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--ignore-certificate-errors");
    }

    static WebDriver createDriver() {
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.executeCdpCommand("Network.enable", Map.of());
        driver.executeCdpCommand("Network.setExtraHTTPHeaders", Map.of("headers", Map.of("accept-language", "en-US,en;q=0.9")));

        return driver;
    }

}
