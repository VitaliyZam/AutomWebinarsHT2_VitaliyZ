package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.util.List;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {
    private static String BROWSER = BrowserType.CHROME;
    private static List<String> errors;

    public void addError(String error) {
        errors.add(error);
        System.out.println(error);
    }

    public static String getBROWSER() {
        return BROWSER;
    }
    /**
     *
     * @return New instance of {@link WebDriver} object.
     */
    public static WebDriver getDriver() {
        // TODO return  WebDriver instance
        //throw new UnsupportedOperationException("Method doesn't return WebDriver instance");
        switch (BROWSER) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();

            case "iexplore":
                System.setProperty("webdriver.ie.driver",
                        new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                return new InternetExplorerDriver();

            case "MicrosoftEdge":
                System.setProperty("webdriver.edge.driver",
                        new File(BaseScript.class.getResource("/MicrosoftWebDriver.exe").getFile()).getPath());
                return new EdgeDriver();

            default: {
                System.setProperty("webdriver.chrome.driver",
                        new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
            }
        }
    }
}
