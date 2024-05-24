package pageobject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.constants.HomePageConstants;

public class WebDriverManager {
    protected WebDriver driver;
    private final String site = HomePageConstants.SITE_URL;

    @Before
    public void setUp() {
        driver = new FirefoxDriver(); // Сменить на ChromeDriver(); для тестов в Google Chrome - нельзя оформить заказ из-за бага
        driver.get(site);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
