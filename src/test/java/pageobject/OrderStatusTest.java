package pageobject;

import org.junit.Test;
import pageobject.constants.HomePageConstants;

import static org.junit.Assert.assertEquals;


public class OrderStatusTest extends WebDriverManager {
    private final String numberOrder = "aaaaa";

    @Test
    public void orderStatusWithoutNumber() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber(numberOrder)
                .clickGo();
        new OrderStatus(driver)
                .waitLoadOrderStatusPage();
        assertEquals(HomePageConstants.TRACKING_PAGE_URL, driver.getCurrentUrl());
    }
}