import pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaySong extends BaseTest {
    @Test
   public void playSongContextMenu()  {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.login().clickOnAllSongs().contextClickFirstSong().playFromContextMenu();
        Assert.assertTrue(homePage.isSongPlaying());
   }
    @Test
    void hoverOverPlayButton(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.login().clickOnAllSongs().hoverPlay().click();
    }
    @Test
    public void listOfSongWebElements(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        LikedPage likedPage = new LikedPage(getDriver());
        loginPage.login().choosePlayListByName("super");
        likedPage.getListOfTheSongs();
        System.out.println("LIST OF SONGS====="+ likedPage.getListOfTheSongs().size());
        Assert.assertEquals(likedPage.getListOfTheSongs().size(),3);
    }
    @Test
    public void changeNameOfPlayList() {

        emailLogin();
        doubleClickPlayList();
        enterPlayListName();
        Assert.assertTrue(doesPlayListExist());
    }

    private boolean doesPlayListExist() {
        WebElement playListElement = getDriver().findElement(By.xpath("//a[text()='Super3333']"));
        return playListElement.isDisplayed();
    }

    private void enterPlayListName(){
        By playListInputfield = By.cssSelector("input[name='name']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(playListInputfield));
        WebElement playListInput = getDriver().findElement(By.cssSelector("input[name='name']"));
        playListInput.sendKeys((Keys.chord(Keys.COMMAND,"a", Keys.BACK_SPACE)));
        playListInput.sendKeys("Super3333");
        playListInput.sendKeys(Keys.ENTER);

    }

    private void doubleClickPlayList() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#playlists li:nth-child(3)")));
        WebElement element = getDriver().findElement(By.cssSelector("#playlists li:nth-child(3)"));
        actions.doubleClick(element).perform();

    }

}
