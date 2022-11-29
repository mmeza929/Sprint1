package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    By avatarLocator = By.cssSelector("img.avatar");
    @FindBy(css = "[data-testid='sidebar-create-playlist-btn']")
    WebElement createPlaylistButton;

    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    WebElement newPlaylistOption;

    @FindBy(css = "[name='name']")
    WebElement playlistNameField;

    @FindBys(
            @FindBy(css = "#playlists a[href*='playlist']")
    )
    List<WebElement> playlists;

    @FindBy(xpath = "//button[@data-testid='home-view-all-recently-played-btn']")
    WebElement viewAllBtn;

    @FindBy(xpath = "//section[@id='recentlyPlayedWrapper']//tr[@class='song-item']//td[@class='title']")
    WebElement getTitleTxt;

    @FindBy(xpath = "//section[@id='recentlyPlayedWrapper']//tr[@class='song-item']")
    WebElement songList;

    @FindBy(xpath = "//button[contains(@title, 'Add selected songs')]")
    WebElement addToPlaylist;


    @FindBy(xpath = "//section[@id='recentlyPlayedWrapper']//li[contains(text(), 'super')]")
    WebElement superPlaylist;


    @FindBy(xpath = "//a[text()='super']")
    WebElement superPlaylistLink;

    @FindBy(xpath = "//section[@id='playlistWrapper']//tr[@class='song-item']//td[@class='title']")
    WebElement superPlaylistSong;

    @FindBy(xpath = "//i[@data-testid='play-next-btn']")
    WebElement nextBtn;

    @FindBy(xpath = "//span[@data-testid='play-btn']")
    WebElement playPauseBtn;

    @FindBy(xpath = "//button[@title='Click for a marvelous visualizer!']")
    WebElement visualizer;


    @FindBy(css = ".playlist:nth-child(1)")
    WebElement myPlaylist;

    @FindBy(css= ".btn-delete-playlist")
    WebElement delPlaylist;

    @FindBy(css= "div.success.show")
    WebElement popupMessage;


    public HomePage(WebDriver givenDriver){
        super(givenDriver);

    }
    public WebElement getUserAvatar()
    {
        return driver.findElement(avatarLocator);
    }
    public boolean isUserAvatarDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarLocator)).isDisplayed();

        return driver.findElement(avatarLocator).isDisplayed();
    }
    public void createPlaylist(String name) throws InterruptedException {
        createPlaylistButton.click();
        newPlaylistOption.click();
        playlistNameField.sendKeys(name);
        playlistNameField.sendKeys(Keys.RETURN);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[name='name']")));
    }

    public int getNumberOfPlaylist() {
        return playlists.size();
    }





public void addSongToPlaylist() throws InterruptedException{

    viewAllBtn.click();
    String getSongTitle = getTitleTxt.getText();
    songList.click();
    addToPlaylist.click();
    superPlaylist.click();
    superPlaylistLink.click();


        //Verify the song
       String songFromSuperPlaylist = superPlaylistSong.getText();
      Assert.assertEquals(songFromSuperPlaylist, getSongTitle);
   }

   public void playASong() throws InterruptedException{

       nextBtn.click();
       Thread.sleep(2000);
       playPauseBtn.click();
       Assert.assertTrue(visualizer.isDisplayed());
       }

    public void selectPlaylist() throws InterruptedException {

        myPlaylist.click();
    }

    public void deletePlaylist() throws InterruptedException {

        myPlaylist.click();
        Thread.sleep(5000);
        delPlaylist.click();
        Thread.sleep(4000);
       String getConfirmationPopup = popupMessage.getText();
        Assert.assertEquals(getConfirmationPopup, "Deleted playlist");
    }




}