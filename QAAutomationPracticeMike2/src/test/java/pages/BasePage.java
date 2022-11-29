package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

     WebDriver driver;
     WebDriverWait wait;
     Actions actions;
    By allSongLocator = By.cssSelector("li a.songs");
    By songVisualizer = By.cssSelector("[data-testid = 'sound-bar-play']");
     public BasePage(WebDriver givenDriver){
         driver=givenDriver;
         wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         actions = new Actions(driver);
         PageFactory.initElements(driver, this);
     }





     public AllSongs clickOnAllSongs ()  {
//         WebElement loadingScreen = driver.findElement(By.cssSelector("#overlay"));
//         wait.until(ExpectedConditions.invisibilityOf(loadingScreen));
//then click all songs
         wait.until(ExpectedConditions.visibilityOfElementLocated(allSongLocator));
         driver.findElement(allSongLocator).click();
         return new AllSongs(driver);


     }
    public boolean isSongPlaying(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(songVisualizer));
        WebElement songVisualizer1 = driver.findElement(songVisualizer);
        return songVisualizer1.isDisplayed();
    }
    public void getContextMenu() {
        By songLocator = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(songLocator));
        WebElement firstSong = driver.findElement(songLocator);
        actions.contextClick(firstSong).perform();
    }
    public BasePage choosePlayListByName(String name) {
        By playListLocator = By.xpath( "//a[contains(text(), '" + name + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(playListLocator)).click();
        return this;
    }


}
