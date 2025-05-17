package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

import java.util.List;

public class DialogContent extends ReusableMethods {
    public DialogContent() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    //US_04_CheckIn_CheckOutPOM
    @FindBy(xpath = "(//*[@id='lang_sel_list']//img)[6]")
    public WebElement englishIcon;

    @FindBy(css = "[data-cli_action='accept']")
    public WebElement acceptCookies;

    @FindBy(name = "widget_date")
    public WebElement checkInWidget;

    @FindBy(name="widget_date_to")
    public WebElement checkOutWidget;

    @FindBy(name="name='widget_date'")
    public WebElement checkInDate;

    @FindBy(xpath = "(//*[starts-with(@class,'pika-single')])[1]//td[@data-day]")
    public List<WebElement> checkIndateList;

    @FindBy(xpath = "(//*[starts-with(@class,'pika-single')])[2]//td[@data-day]")
    public List<WebElement> checkOutdateList;

    @FindBy(xpath = "(//select[@class='pika-select pika-select-year'])[1]//option")
    public List<WebElement> checkInYearNames;

    @FindBy(xpath = "(//select[@class='pika-select pika-select-year'])[2]//option")
    public List<WebElement> checkoutYearNames;

    @FindBy(xpath = "(//select[@class='pika-select pika-select-month'])[1]//option")
    public List<WebElement> checkInMonthNames;

    @FindBy(xpath = "(//select[@class='pika-select pika-select-month'])[2]//option")
    public List<WebElement> checkOutMonthNames;

    @FindBy (xpath = "(//*[@class='pika-next'])[1]")
    public WebElement checkInNextButton;

    @FindBy(xpath = "(//*[@class='pika-next'])[2]")
    public WebElement checkOutNextButton;

    @FindBy (xpath = "(//button[starts-with(@class,'pika-prev')])[1]")
    public WebElement checkInPrevButton;

    @FindBy(css="[class='submit_link']")
    public WebElement goButton;

    @FindBy(css = "[class='hotel_name ']")
    public WebElement hotelCastleRastattText;

    @FindBy(css = "[class='message_container']")
    public List<WebElement> messageContainer;

    @FindBy(css = "[class='av-left-rooms '] ")
    public List<WebElement> availableRoom;

    @FindBy(css = "[class='spinner_container']")
    public WebElement spinnerContainer;



}
