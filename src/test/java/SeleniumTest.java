import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SeleniumTest {

    //-----------------------------------Global Variables-----------------------------------
    // test URL variable
    public String testURL = "https://www.cars.com/";

    //-----------------------------------Test Setup-----------------------------------
    public void pause (int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        }
    }

    @BeforeMethod
    public void setupTest (){
        System.setProperty("webdriver.chrome.driver", "artifacts\\chromedriver.exe");
        System.setProperty("webdriver.ie.driver", "artifacts\\IEDriverServer.exe");

        Configuration.browser = "chrome";
        //Configuration.browser = "ie";
        Configuration.timeout = 7000;
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void theTest () {
        open(testURL);
        $(byAttribute("data-linkname", "Advanced Search")).click();
        $(byAttribute("ng-model", "selections.rd")).click();
        $(byText("20 miles of")).click();
        ////div/div/div/img
        $(byAttribute("name", "zipField")).sendKeys("91301");
        $(byXpath("//*[@value='cpo']/../..")).click();
        $(byAttribute("for", "as-all-styles")).click();
        $(byAttribute("name", "make")).selectOption("BMW");
        $(byAttribute("data-linkname", "add-another-car")).click();
        $(byXpath("(//*[@ng-change='makeChange($index)'])[2]")).selectOption("Honda");
        $(byAttribute("ng-model", "selections.clrId")).selectOption("Pink");
        $(byXpath("//*[contains(text(),'Electric')]")).click();
        $(byAttribute("ng-model", "selections.kw")).sendKeys("Dzhigurda");
        $(byAttribute("ng-model", "asForm.kwm")).selectOption("Exact Phrase");
        $(byAttribute("value", "Search")).click();

        pause(10);
        Assert.assertEquals($$(byText("No results")).size(), 0);

        getWebDriver().navigate().back();

        $(byAttribute("data-linkname", "start-over")).click();
        $(byAttribute("name", "zipField")).sendKeys("91301");
        $(byAttribute("value", "Search")).click();

        pause(10);
        Assert.assertTrue($$(byAttribute("data-linkname", "md-thumb")).size() > 1);

    }

}