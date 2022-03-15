package pages;

import functions.Logs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WinePage {
    WebDriver driver;
    public WinePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Logs.log("Initializing WinePage");
    }


    @FindBy(xpath = "//span[@class='headline']")
    public WebElement headline;

    @FindBy(xpath = "//span[@class='vintage']")
    public WebElement vintage;

    @FindBy(xpath = "//div[contains(@class,'vivinoRating__averageValue')]")
    public WebElement averageValue;

    @FindBy(xpath = "//div[contains(@class,'vivinoRating__starsAndCaption')]")
    public WebElement ratings;

    @FindBy(xpath = "//div[contains(@class,'breadCrumbs__breadCrumbs')]")
    public WebElement characteristic;

}
