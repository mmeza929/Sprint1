import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String url;
    public static Actions actions;
    public static ThreadLocal<WebDriver> threadDriver;



    @BeforeSuite
    public static void chromeConfigs() {
        // This is for Windows users
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
    }
    //    @BeforeMethod
//    public static void launchBrowser() throws MalformedURLException {
//        System.setProperty("webdriver.gecko.driver", "geckodriver");
//        //driver = new ChromeDriver();
//        //driver = new FirefoxDriver();
//        //driver = new SafariDriver();
//        threadDriver = new ThreadLocal<>();
//        driver = pickBrowser(System.getProperty("browser"));
//        threadDriver.set(driver);
//        //actions = new Actions(getDriver());
//        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//        url = "https://bbb.testpro.io/";
//        getDriver().get(url);
//
//
//    }
    @BeforeMethod
    public void setUpBrowser() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName","firefox");
        capabilities.setCapability("browserName","chrome");
        url = "https://bbb.testpro.io/";
        threadDriver = new ThreadLocal<>();
        driver = pickBrowser(System.getProperty("browser"));
        threadDriver.set(driver);
        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }




    private static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL ="http://192.168.1.69:4444";
        switch (browser){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                return driver = new FirefoxDriver();
            case "safari":
                return driver = new SafariDriver();
            case "grid-safari":
                caps.setCapability("browserName", "safari");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
         //   case "cloud":
         //       return lambdaTest();
            default:
                return driver = new ChromeDriver();
        }
    }
    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    @AfterMethod
    public  void tearDownBrowser(){

        getDriver().quit();
        threadDriver.remove();

    }


    public static void emailLogin() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        emailField.click();
        emailField.sendKeys("hammer2995@gmail.com");
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
        passwordField.click();
        passwordField.sendKeys("te$t$tudent");
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        submitButton.click();



    }
    public static void newPlaylist() {
        WebElement newPlayList = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='fa fa-plus-circle create']")));
        newPlayList.click();
        WebElement createPlayList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='New Playlist']")));
        createPlayList.click();
        WebElement inputPlayList = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[placeholder='↵ to save']")));
        inputPlayList.click();
        inputPlayList.sendKeys("super");
        inputPlayList.sendKeys(Keys.RETURN);
    }

    @DataProvider( name="invalidCredentials")
    public static Object[][] getCredentials(){
        return new Object[][]{
                {"invalid@class.com", "invalispass"},{"dfdfs@clas.com", ""},{"",""}
        };

    }
    private void selectPlaylist() throws InterruptedException {
        WebElement myPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        myPlaylist.click();
        Thread.sleep(5000);
    }
}
