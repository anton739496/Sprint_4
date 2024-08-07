package pageobject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.constants.ScooterColours;


import static org.junit.Assert.assertTrue;
import static pageobject.constants.CreateOrderButton.UP_BUTTON;
import static pageobject.constants.RentDurationConstants.*;
import static pageobject.constants.ScooterColours.*;

@RunWith(Parameterized.class)
public class OrderCreateTest extends WebDriverManager {
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final ScooterColours colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public OrderCreateTest(Enum button, String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, ScooterColours colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "ИмяА", "ФамилияА", "Адрес1", 1, "79990000001", "01.03.2024", ONE_DAY, GREY, "comments one"},
                {UP_BUTTON, "ИмяБ", "ФамилияБ", "Адрес2", 2, "79990000002", "01.03.2024", FOUR_DAYS, BLACK, "comments two"},
                {UP_BUTTON, "ИмяВ", "ФамилияВ", "Адрес3", 3, "79990000003", "01.03.2024", SIX_DAYS, GREY, "comments three"},
        };
    }

    @Test
    public void testCreateOrderWithUpButtonSuccess() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(button);

        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}