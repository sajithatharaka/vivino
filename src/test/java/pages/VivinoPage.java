package pages;

import functions.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VivinoPage {
    WebDriver driver;
    public VivinoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Logs.log("Initializing VivinoPage");
    }

    @FindBy(name = "q")
    private WebElement search;

    @FindBys(@FindBy(
            xpath = "//div[@class='card card-lg']"
    ))
    public List<WebElement> resultCards;

    public WebElement search(String keyword){
        search.sendKeys(keyword);
        search.sendKeys(Keys.ENTER);
        return search;
    }

    public WebElement itemTitle(WebElement itemCard){
       return itemCard.findElement(By.xpath(".//span[@class='header-smaller text-block wine-card__name']/a/span"));
    }

    public WebElement itemCountry(WebElement itemCard){
        return itemCard.findElement(By.xpath(".//span[@class='text-block wine-card__region']/a[@data-item-type='country']"));
    }

    public WebElement itemAverageRating(WebElement itemCard){
        return itemCard.findElement(By.xpath(".//div[@class='average__container']/div"));
    }
}
