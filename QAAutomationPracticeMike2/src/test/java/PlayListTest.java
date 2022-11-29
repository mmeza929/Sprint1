import pages.HomePage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayListTest extends BaseTest {
    @Test
    public void createPlaylist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        int initialNumberOfPlaylists = homePage.getNumberOfPlaylist();
        homePage.createPlaylist("name");
        int updatedNumberOfPlaylists = homePage.getNumberOfPlaylist();
        Assert.assertEquals(updatedNumberOfPlaylists, initialNumberOfPlaylists + 1, "Playlist not created");
    }

    @Test
    public void createPlaylistshort() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        int initialNumberOfPlaylists = homePage.getNumberOfPlaylist();
        homePage.createPlaylist("na");
        int updatedNumberOfPlaylists = homePage.getNumberOfPlaylist();
        Assert.assertEquals(updatedNumberOfPlaylists, initialNumberOfPlaylists, "Playlist created!!!!!!");
    }

    @Test
    public void createPlaylistlong() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = loginPage.login();
        int initialNumberOfPlaylists = homePage.getNumberOfPlaylist();
        homePage.createPlaylist("namenamenamenam");
        int updatedNumberOfPlaylists = homePage.getNumberOfPlaylist();
        Assert.assertEquals(updatedNumberOfPlaylists, initialNumberOfPlaylists, "Playlist created!!!!!!");
    }

}