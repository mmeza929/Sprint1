package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LikedPage extends BasePage
{
    public LikedPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public List getListOfTheSongs() {

        return driver.findElements(By.cssSelector("#playlistWrapper td.title"));
    }
}
