package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Locale;

public class GWD {
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();
    public static ThreadLocal<String> threadLanguage = new ThreadLocal<>();


    public static WebDriver getDriver() {

        String language = ConfigReader.getProperty("language");
        threadLanguage.set(language);

        Locale.setDefault(new Locale(language));
        System.setProperty("user.language", language);

        if (threadDriver.get() == null) {
            String browser = threadBrowserName.get();
            if (browser == null) {
                browser = ConfigReader.getProperty("browser");
                threadBrowserName.set(browser);
            }

            switch (browser.toLowerCase()) {
                case "firefox":
                    threadDriver.set(setFirefoxOptions(language));
                    break;
                case "edge":
                    threadDriver.set(new EdgeDriver());
                    break;
                case "chrome":
                    threadDriver.set(setChromeOptions(language));
                    break;
            }
            threadDriver.get().manage().window().maximize();
            threadDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntProperty("pageLoadTimeout")));
        }
        return threadDriver.get();
    }

    public static WebDriver setChromeOptions(String lang) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + lang);
        options.addArguments("--disable-blink-features=AutomationControlled");
        return new ChromeDriver(options);
    }

    public static WebDriver setFirefoxOptions(String lang) {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("intl.accept_languages", lang);
        return new FirefoxDriver(options);
    }

    public static void quitDriver() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            WebDriver driver = threadDriver.get();
            driver = null;
            threadDriver.set(driver);
        }
    }

}
