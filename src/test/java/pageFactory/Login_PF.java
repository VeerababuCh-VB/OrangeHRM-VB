package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login_PF {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    @FindBy(xpath = "(//span[contains(@class,'oxd-text oxd-text--span')])[2]")
    private WebElement pimButton;

    @FindBy(xpath = "//h6[text()='PIM']")
    private WebElement pimHeader;

    @FindBy(xpath = "//i[contains(@class,'oxd-icon bi-caret-down-fill')]")
    private WebElement logoutIcon;

    @FindBy(xpath = "//a[normalize-space(text())='Logout']")
    private WebElement logoutButton;

    @FindBy(css = "div.orangehrm-login-branding")
    private WebElement brandingElement;
    
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    public WebElement addbtn;
    
    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addHeader;

    
    @FindBy(xpath = "//img[@alt='company-branding']")
    private WebElement companyLogo;
    
    @FindBy(name = "firstName")  
    public WebElement firstNameInput;

    @FindBy(name = "lastName")   
    public WebElement lastNameInput;

    @FindBy(xpath = "(//span[text()='Required'])[1]")
	public WebElement firstNameError;

    @FindBy(xpath = "(//span[text()='Required'])[2]")
    public WebElement lastNameError;
 
    @FindBy(xpath = "//button[text()=' Save ']")
    public  WebElement savebtn;
    
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    public WebElement idinput;
    
    public Login_PF(WebDriver driver) {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
    }

    public void enterUserName(String userName) {
        usernameField.clear();
        usernameField.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickPim() {
        pimButton.click();
    }

    public void clickLogoutButton() {
        logoutIcon.click();
        logoutButton.click();
    }
    
    public void savebtn() {
    	savebtn.click();
    }
    
    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getPimButton() {
        return pimButton;
    }

    public WebElement getPimHeader() {
        return pimHeader;
    }

    public WebElement getLogoutIcon() {
        return logoutIcon;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getBrandingElement() {
        return brandingElement;
    }
    
    public WebElement getaddHeader() {
        return addHeader;
    }
    
    public WebElement getCompanyLogo() {
        return companyLogo;
    }

}