package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.className;

@Feature("Selenide-appium Android tests")
@Story("Search tests")
@Tag("android")
class SearchTests extends TestBase {

    @Test
    //@Disabled("Example without steps")
    @DisplayName("Successful search in wikipedia android app")
    void successfulSearch() {
        open();

//        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.elementToBeClickable(AccessibilityId("Search Wikipedia")));
//        searchElement.click();
        $(AccessibilityId("Search Wikipedia")).click();

//        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
//        insertTextElement.sendKeys("BrowserStack");
        $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("BrowserStack");

//        Thread.sleep(5000);
//        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
//        assert(allProductsName.size() > 0);
        $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
    }

    @Test
    @DisplayName("Successful search in wikipedia android app")
    void successfulSearchWithoutSteps() {
        step("Open application", ()-> open());

        step("Type search", ()-> {
            $(AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("Verify content found", ()->
                $$(className("android.widget.TextView"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    //@Disabled("Java + Appium")
    @DisplayName("Successful search in wikipedia android app")
    void successfulSearchDisabled() throws InterruptedException, MalformedURLException {
        // Move to TestBase
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "iPhone 11 Pro");
        caps.setCapability("os_version", "13");
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", "bs://444bd0308813ae0dc236f8cd461c02d3afa7901d");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://userName:accessKey@hub-cloud.browserstack.com/wd/hub"), caps);

        // Test
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);
        List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
        assert(allProductsName.size() > 0);
    }
}
