package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {
    private static String BROWSER = BrowserType.CHROME;
    private static List<String> errors = new ArrayList<>();

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
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
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

    /**
     * Created by User on 08.04.2017.
     */

}
