package script;

import functions.Logs;
import functions.RandomData;
import functions.SUTManager;
import functions.vo.Item;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.VivinoPage;
import pages.WinePage;

import java.util.ArrayList;

public class VerifyVivinoSearchFunctionality {
    WebDriver driver;
    int item = RandomData.getNumberBetween(20);

    @DataProvider(name = "search-keyword")
    private Object[][] getDataFromDataProvider() {
        return new Object[][]
                {
                        {"Red"}
                };
    }

    @BeforeTest
    private void init() {
        //Opening the Browser and Maximizing with the help of WebDriverManager
        WebDriverManager webDriverManager = WebDriverManager.chromedriver();
        webDriverManager.setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "search-keyword")
    public void verifySearch(String keyword) throws Exception {
        driver.get(SUTManager.getSUT("PRD")); //Navigate to https://www.vivino.com

        VivinoPage vivinoPage = new VivinoPage(driver); //Initializing VivinoPage
        vivinoPage.search(keyword); //Enter `<keyword>` into the search input and search

        //Iterating through every listed element, extracting title, country and average price and adding into ArrayList<Item>
        ArrayList<Item> items = new ArrayList<>();
        vivinoPage.resultCards.forEach((element) -> {
                    Item item = new Item();
                    item.setTitle(vivinoPage.itemTitle(element).getText());
                    item.setCountry(vivinoPage.itemCountry(element).getText());
                    item.setAverageRating(vivinoPage.itemAverageRating(element).getText());
                    items.add(item);
                }
        );

        //Stdout attributes containing/not containing the key word
        for (Item item : items) {
            if (item.getTitle().contains(keyword)) {
                Logs.log("[Contains the keyword]Title " + item.getTitle() + " keyword : " + keyword);
            } else {
                Logs.log("[Does Not contain the keyword]Title " + item.getTitle() + " keyword : " + keyword);
            }

            if (item.getCountry().contains(keyword)) {
                Logs.log("[Contains the keyword] Country " + item.getCountry() + " keyword : " + keyword);
            } else {
                Logs.log("[Does Not contain the keyword]Title " + item.getCountry() + " keyword : " + keyword);
            }
        }

        vivinoPage.itemTitle(vivinoPage.resultCards.get(item)).click(); //Click on random wine's title

        WinePage winePage = new WinePage(driver);//Initializing WinePage

        //Extracting info from the wine page
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOf(winePage.headline));

        String headline = winePage.headline.getText();
        String vintage = winePage.vintage.getText();
        String averageValue = winePage.averageValue.getText();
        String ratings = winePage.ratings.getText();
        String characteristic = winePage.characteristic.getText();
        Logs.log("Headline : " + headline + " Vintage: " + vintage + " Average Value: " + averageValue + " Ratings: " + ratings + " Characteristic: " + characteristic);

        boolean containsKeyword = false;
        //Iterating
        for (Item item : items) {
            if (item.getTitle().contains(headline)) {
                Logs.log("Headline present in main page and wine page : " + headline);
                containsKeyword = true;
            }
        }
        //Check whether at least one attribute contains `<keyword>`
        Assert.assertEquals(containsKeyword, true, "No Attribute contains the keyword " + keyword);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();// Quitting the driver
    }
}