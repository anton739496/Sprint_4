package pageobject;

import org.junit.Test;
import org.openqa.selenium.By;
import pageobject.constants.HomePageConstants;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class YandexButtonTest extends WebDriverManager {

    @Test
    public void clickYandexFromAboutRenterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .clickYandex();

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(HomePageConstants.LOGO_LINK_XPATH));
        String currentUrl = driver.getCurrentUrl();

        assertEquals(HomePageConstants.DZEN_URL, currentUrl);
    }

    @Test
    public void clickYandexFromAboutScooterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);
        AboutScooter aboutScooter = new AboutScooter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .inputName("Имя")
                .inputSurname("Фамилия")
                .inputAddress("Адрес")
                .changeStateMetro(77)
                .inputTelephone("+79999999999")
                .clickNextButton();

        aboutScooter.waitAboutRentHeader()
                .clickYandex();

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(HomePageConstants.LOGO_LINK_XPATH));
        String currentUrl = driver.getCurrentUrl();

        assertEquals(HomePageConstants.DZEN_URL, currentUrl);
    }

    @Test
    public void clickYandexFromOrderStatusPage() {
        HomePage homePage = new HomePage(driver);
        OrderStatus orderStatus = new OrderStatus(driver);

        homePage.waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber("12345")
                .clickGo();

        orderStatus.waitLoadOrderStatusPage()
                .clickYandex();

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(HomePageConstants.LOGO_LINK_XPATH));
        String currentUrl = driver.getCurrentUrl();

        assertEquals(HomePageConstants.DZEN_URL, currentUrl);
    }
}