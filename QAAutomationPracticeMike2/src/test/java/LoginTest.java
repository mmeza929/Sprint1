import pages.AllSongs;
import pages.HomePage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test(enabled = true)
    public void LoginWithValidEmailPasswordTest(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        Assert.assertTrue(homePage.isUserAvatarDisplayed());

    }
    @Test(enabled = true)
    public void Shuffle(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        AllSongs allSongs = new AllSongs(getDriver());
        homePage.clickOnAllSongs();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        allSongs.shuffle();
        Assert.assertTrue((homePage.isSongPlaying()));
    }
    @Test(enabled = false)
    public void PlayASongFromAllSongs() throws Exception{
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        homePage.clickOnAllSongs().doubleClickFirstSong();
        Assert.assertTrue(homePage.isSongPlaying());


    }
    @Test(enabled = false)
    public void playFromContest(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        homePage.clickOnAllSongs().contextClickFirstSong().playFromContextMenu();
        Assert.assertTrue(homePage.isSongPlaying());
    }

    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();


    }


}
