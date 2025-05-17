package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.DialogContent;
import utilities.ConfigReader;
import utilities.GWD;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


public class _US04_CheckIn_CheckOut_Steps {

    DialogContent cc = new DialogContent();
    LocalDate today = LocalDate.now();
    YearMonth currentMonth = YearMonth.now();
    int dayOfMonth = today.getDayOfMonth();
    String dayOfMont = String.valueOf(dayOfMonth);
    String year = String.valueOf(today.getYear());
    String month = String.valueOf(today.getMonth());
    List<Integer> checkInPreviousDay = new ArrayList<>();
    List<String> previousDayStr = new ArrayList<>();
    int lastDayOfMonth = currentMonth.lengthOfMonth();
    int randomCheckInDate = (int) (Math.random() *(lastDayOfMonth-dayOfMonth+1))+dayOfMonth;
    List<Integer> checkOutPreviousDays = new ArrayList<>();
    int randomCheckOutDate = (int) (Math.random() *(lastDayOfMonth-randomCheckInDate+1))+randomCheckInDate;
    String randomCheckInDateStr = String.valueOf(randomCheckInDate);
    String randomCheckOutDateStr = String.valueOf(randomCheckOutDate);
    String checkInDate = randomCheckInDateStr+"/"+String.format("%02d",today.getMonthValue())+"/"+year;
    String checkOutDate = randomCheckOutDateStr+"/"+String.format("%02d",today.getMonthValue())+"/"+year;

    @Given("Navigate to the Hotel Castle Rastatt")
    public void navigateToTheHotelCastleRastatt() {
        GWD.getDriver().get(ConfigReader.getProperty("URL"));
        cc.clickFunction(cc.acceptCookies);

    }

    @When("The customer click  on the english language icon")
    public void theCustomerClickOnTheEnglishLanguageIcon() {
        cc.clickFunction(cc.englishIcon);

    }

    @And("The customer clicks on the check-in calender icon")
    public void theCustomerClicksOnTheCheckInCalenderIcon() {
        cc.clickFunction(cc.checkInWidget);

    }

    @Then("Verifies that today's date, month and previous days cannot be selected")
    public void verifiesThatTodaySDateMonthAndPreviousDaysCannotBeSelected() {
        dayOfMonth = today.getDayOfMonth();
        dayOfMont = String.valueOf(dayOfMonth);
        year = String.valueOf(today.getYear());
        month = String.valueOf(today.getMonth());
        checkInPreviousDay = new ArrayList<>();
        previousDayStr = new ArrayList<>();

        for (int i = dayOfMonth; i >0 ; i--) {
            checkInPreviousDay.add(i);
        }


        for (int i = 0; i < cc.checkIndateList.size(); i++) {

            if (checkInPreviousDay.get(i)<dayOfMonth){
                cc.wait.until(ExpectedConditions.visibilityOf(cc.checkIndateList.get(i)));
                Assert.assertEquals(cc.checkIndateList.get(i).getAttribute("class"),"is-disabled");
                break;
            }
        }
        for(WebElement day : cc.checkIndateList){
            if (day.getText().equals(dayOfMont)){
                cc.wait.until(ExpectedConditions.visibilityOf(day));
                Assert.assertEquals(day.getAttribute("class"), "is-today is-selected");
                break;
            }
        }

        for (WebElement Month:cc.checkInMonthNames){
            if (Month.getText().toUpperCase().equals(month)){
                Assert.assertTrue(Month.isSelected());
                Assert.assertEquals(cc.checkInPrevButton.getAttribute("class"),"pika-prev is-disabled");
                break;
            }
        }

        for(WebElement Year:cc.checkInYearNames){
            if (Year.getText().equals(year)){
                Assert.assertTrue(Year.isSelected());
                break;
            }
        }
    }

    @And("The customer enters  the check-in date")
    public void theCustomerEntersTheCheckInDate() {
        System.out.println(randomCheckInDate);
        cc.clickFunction(cc.checkIndateList.get(randomCheckInDate-1));
    }

    @And("The customer clicks on the check-out calender icon")
    public void theCustomerClicksOnTheCheckOutCalenderIcon() {
        cc.clickFunction(cc.checkOutWidget);


    }

    @Then("Verifies that today's date, month, previous days and entry date cannot be selected")
    public void verifiesThatTodaySDateMonthPreviousDaysAndEntryDateCannotBeSelected() {

            for (int i = randomCheckInDate; i > 0; i--) {
                checkOutPreviousDays.add(i);
            }

            for (int i = 0; i < cc.checkOutdateList.size(); i++) {

                if (checkOutPreviousDays.get(i) < randomCheckInDate) {
                    cc.wait.until(ExpectedConditions.visibilityOf(cc.checkOutdateList.get(i)));
                    Assert.assertEquals(cc.checkOutdateList.get(i).getAttribute("class"), "is-disabled");
                    break;
                }
            }

            for (WebElement Year : cc.checkoutYearNames) {
                if (Year.getText().equals(year)) {
                    Assert.assertTrue(Year.isSelected());
                    break;
                }
            }

    }

    @And("The customer enters  the check-out date")
    public void theCustomerEntersTheCheckOutDate() {
        if (cc.checkIndateList.get(randomCheckInDate-1).getText().equals(String.valueOf(lastDayOfMonth))){
            System.out.println(cc.checkIndateList.get(randomCheckInDate-1).getText());
            cc.clickFunction(cc.checkOutdateList.get((int) (Math.random() *(lastDayOfMonth))+1));

        }else {
            System.out.println(randomCheckOutDate);
            cc.clickFunction(cc.checkOutdateList.get(randomCheckOutDate - 1));
        }

    }

    @Then("Verifies check-in and check-out dates")
    public void verifiesCheckInAndCheckOutDates() {
        cc.wait.until(ExpectedConditions.attributeContains(cc.checkInWidget,"value","2025"));
        cc.wait.until(ExpectedConditions.attributeContains(cc.checkOutWidget,"value","2025"));
        System.out.println(cc.checkInWidget.getAttribute("value"));
        System.out.println(cc.checkOutWidget.getAttribute("value"));
        System.out.println(checkInDate);
        System.out.println(checkOutDate);


        Assert.assertEquals(cc.checkInWidget.getAttribute("value"), checkInDate);
        cc.waitUntilVisibilityOf(cc.checkOutWidget);
        Assert.assertEquals(cc.checkOutWidget.getAttribute("value"), checkOutDate);
    }

    @When("The customer clicks the GO button")
    public void theCustomerClicksTheGoButton() {
        cc.clickFunction(cc.goButton);
    }

    @Then("Verify redirection to the reservation page")
    public void verifyRedirectionToTheReservationPage() {
        cc.switchToWindow(1);
        String noReservation = "Es tut uns leid, aber für das angegebene Datum sind keine Unterkünfte verfügbar!";
        cc.wait.until(ExpectedConditions.urlContains("reservation"));
        cc.verifyContainsText(cc.hotelCastleRastattText,"Rastatt");
        System.out.println(cc.hotelCastleRastattText.getText());
        cc.wait.until(ExpectedConditions.attributeToBe(cc.spinnerContainer,"style","display: none;"));
        if (!cc.messageContainer.isEmpty()){
            Assert.assertTrue(cc.messageContainer.get(0).getText().contains(noReservation),"Expected message not found");
            System.out.println(cc.messageContainer.get(0).getText());
        }else if (!cc.availableRoom.isEmpty()){
            Assert.assertTrue(cc.availableRoom.get(0).getText().contains("übrig"));
            System.out.println(cc.availableRoom.get(0).getText());
        }
    }
}
