package Tests;

import Utils.BaseScript;
import Utils.Properties;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * Created by User on 01.04.2017.
 */


public class Tests extends BaseScript {
    WebDriver driver;
    private String BaseUrl = Properties.getBaseAdminUrl();
    private String login = Properties.getLogin();
    private String password = Properties.getPassword();

    public WebDriverWait explicitWait(int sec) {
        return new WebDriverWait(this.driver, sec);
    }


    Tests() {
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        this.driver = getDriver();
        PageLoad();
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ScriptA_LoginTest() {
        Login();
        LogOut();
    }
    public void ScriptB_MainMenuTest() {
        Login();
        CheckMenu();
        //LogOut();
        quit();
    }

    public void PageLoad() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to(BaseUrl);
        driver.manage().window().maximize();
    }

    public void Login() {
        String email = "email";
        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#email")));
            WebElement inputEmail = driver.findElement(By.cssSelector("#email"));
            inputEmail.sendKeys(login);
        } catch (NotFoundException e) {
            addError(email + " input not found");
            driver.quit();
        }

        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#passwd")));
            WebElement inputPassword = driver.findElement(By.cssSelector("#passwd"));
            inputPassword.sendKeys(password);
        } catch (NotFoundException e) {
            addError("password input not found");
            driver.quit();
        }

        try {
            WebElement submitBtn = driver.findElement(By.cssSelector("button.btn-primary:nth-child(1)"));
            submitBtn.submit();
        } catch (NotFoundException e) {
            addError("submit button not found");
            driver.quit();
        }
        System.out.println("Log in succeeded");


    }
    public void LogOut() {
        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.className("employee_avatar_small")));
            WebElement avatar = driver.findElement(By.className("employee_avatar_small"));
            avatar.click();
        } catch (NotFoundException e) {
            addError("avatar not found");
        }



        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("header_logout")));
            WebElement logoutBtn = driver.findElement(By.id("header_logout"));
            logoutBtn.click();
            sleep(1000);
            System.out.println("Log out succeeded");

        } catch (NotFoundException e) {
            addError("Logout button not found");
        }
    }


    public void quit() {
        driver.quit();
    }

    public void CheckMenu() {
        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminParentOrders")));
            WebElement ordersTab = driver.findElement(By.id("subtab-AdminParentOrders"));
            ordersTab.click();
            //System.out.println("The \"Orders\" tab is open");
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminParentOrders\" not found");
        }

        try { //checking the correctness of the "Orders" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("process-icon-new")));
            WebElement webElement = driver.findElement(By.className("process-icon-new"));
            String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("process-icon-new")));
            //if (check.equals(driver.findElement(By.className("process-icon-new")).getText())) System.out.println("Refresh of the \"Orders\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Orders\" tab is failed");
        }

        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminCatalog")));
            WebElement catalogueTab = driver.findElement(By.id("subtab-AdminCatalog"));
            catalogueTab.click();
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminCatalog\" not found");
        }


        try { //checking the correctness of the "Catalogue" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("toolbar-icons")));
            WebElement webElement = driver.findElement(By.className("toolbar-icons"));
            String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("toolbar-icons")));
            //if (check.equals(driver.findElement(By.className("toolbar-icons")).getText())) System.out.println("Refresh of the \"Catalogue\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Catalogue\" tab failed");
        }

        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.link-levelone:nth-child(5) > a:nth-child(1)")));
            WebElement customersTab = driver.findElement(By.cssSelector("li.link-levelone:nth-child(5) > a:nth-child(1)"));
            customersTab.click();
        } catch (NotFoundException e) {
            System.out.println("Error:");
            addError("Element with id = \"subtab-AdminParentCustomer\" not found");
        }
        try { //checking the correctness of the "Customers" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("panel-heading")));
            WebElement webElement = driver.findElement(By.className("panel-heading"));
            //String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("panel-heading")));
            //if (check.equals(driver.findElement(By.className("panel-heading")).getText())) System.out.println("Refresh of the \"Customers\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Customers\" tab failed");
        }
        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminParentCustomerThreads")));
            WebElement supportTab = driver.findElement(By.id("subtab-AdminParentCustomerThreads"));
            supportTab.click();
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminParentCustomerThreads\" not found");
        }
        try { //checking the correctness of the "Support" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("textarea-autosize")));
            WebElement webElement = driver.findElement(By.className("textarea-autosize"));
            String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("textarea-autosize")));
            //if (check.equals(driver.findElement(By.className("textarea-autosize")).getText())) System.out.println("Refresh of the \"Support\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Support\" tab failed");
        }

        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminStats")));
            WebElement statsTab = driver.findElement(By.id("subtab-AdminStats"));
            statsTab.click();
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminStats\" not found");
        }
        try { //checking the correctness of the "Statistics" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.name("submitDateDay")));
            WebElement webElement = driver.findElement(By.name("submitDateDay"));
            String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.name("submitDateDay")));
            //if (check.equals(driver.findElement(By.name("submitDateDay")).getText())) System.out.println("Refresh of the \"Statistics\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Statistics\" tab failed");
        }

        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminParentModulesSf")));
            WebElement modulesTab = driver.findElement(By.id("subtab-AdminParentModulesSf"));
            modulesTab.click();
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminParentModulesSf\" not found");
        }
        try { //checking the correctness of the "Modules" tab refresh
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("modules-list-container-all")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("modules-list-container-all")));
            //System.out.println("Refresh of the \"Modules\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Modules\" tab failed");
        }



        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-submenu='46']")));
            WebElement designTab = driver.findElement(By.cssSelector("li[data-submenu='46']"));
            designTab.click();
        } catch (NotFoundException e) {
            addError("Element with id = \"subtab-AdminParentThemes\" not found");
        }
        try { //checking the correctness of the "Design" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("panel-heading")));
            WebElement webElement = driver.findElement(By.className("panel-heading"));
            String check = webElement.getText();
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.className("panel-heading")));
            //if (check.equals(driver.findElement(By.className("panel-heading")).getText())) System.out.println("Refresh of the \"Design\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Design\" tab failed");
        }


        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-submenu='52']")));
            WebElement deliverTav = driver.findElement(By.cssSelector("li[data-submenu='52']"));
            deliverTav.click();
        } catch (NotFoundException e) {
            addError("Element with text = \"Доставка\" not found");
        }
        try { //checking the correctness of the "Доставка" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-carrier-new_carrier")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-carrier-new_carrier")));
            //System.out.println("Refresh of the \"Доставка\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Доставка\" tab failed");
        }


        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-submenu='55']")));
            WebElement payMethTab = driver.findElement(By.cssSelector("li[data-submenu='55']"));
            payMethTab.click();
        } catch (NotFoundException e) {
            addError("Element with text = \"Способ оплаты\" not found");
        }
        try { //checking the correctness of the "Способ оплаты" tab refresh
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='paypal']")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[alt='paypal']")));
            //System.out.println("Refresh of the \"Способ оплаты\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Способ оплаты\" tab failed");
        }


        try {
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li[data-submenu='58']")));
            WebElement internTab = driver.findElement(By.cssSelector("li[data-submenu='58']"));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            sleep(1000);
            internTab.click();
        } catch (NotFoundException e) {
            addError("Element with text = \"International\" not found");
        }
        try { //checking the correctness of the "International" tab refresh
            explicitWait(10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#configuration_fieldset_general")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#configuration_fieldset_general")));
            //System.out.println("Refresh of the \"International\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"International\" tab failed");
        }


        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-submenu='73']")));
            WebElement shopParamTab = driver.findElement(By.cssSelector("li[data-submenu='73']"));
            shopParamTab.click();
        } catch (NotFoundException e) {
            addError("Element with text = \"Shop Parameters\" not found");
        }
        try { //checking the correctness of the "Shop Parameters" tab refresh
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("conf_id_PS_SSL_ENABLED")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("conf_id_PS_SSL_ENABLED")));
            //System.out.println("Refresh of the \"Shop Parameters\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Shop Parameters\" tab failed");
        }

        try {
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-submenu='95']")));
            WebElement configTab = driver.findElement(By.cssSelector("li[data-submenu='95']"));
            configTab.click();
        } catch (NotFoundException e) {
            addError("Element with text = \"Shop Parameters\" not found");
        }
        try { //checking the correctness of the "Конфигурация" tab refresh
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("checkConfiguration")));
            WebElement title = driver.findElement(By.tagName("h2"));
            System.out.println(driver.getTitle());
            driver.navigate().refresh();
            explicitWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.id("checkConfiguration")));
            //System.out.println("Refresh of the \"Конфигурация\" tab succeeded");
        } catch (NotFoundException e) {
            addError("Refresh of the \"Конфигурация\" tab failed");
        }
    }
}
