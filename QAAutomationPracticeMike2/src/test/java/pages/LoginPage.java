package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    //Locators
    @FindBy(css = "[type='email']")
    WebElement emailField;
    @FindBy(css = "[type='password']")
    WebElement  passwordField;
    @FindBy(css ="[type='submit']" )
    WebElement submitButtonLocator;
//passing driver from our Login test Page
    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }
    public LoginPage clickSubmitBtn(){
        wait.until(ExpectedConditions.visibilityOf(submitButtonLocator));
        submitButtonLocator.click();
        return this;
    }
    public LoginPage provideEmail(String email)  {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        return this;

    }
    public LoginPage providePassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        return this;

    }
    public HomePage login(){
        provideEmail("hammer2995@gmail.com").providePassword("te$t$tudent").clickSubmitBtn();
        return new HomePage(driver);
         }




}
