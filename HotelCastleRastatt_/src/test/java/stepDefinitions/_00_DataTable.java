package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import pages.DialogContent;
import pages.Headers;

import java.util.List;

public class _00_DataTable {

    DialogContent dc = new DialogContent();
    Headers hs = new Headers();

    @And("Customer clicks on the element in Headers")
    public void theCustomerClicksOnTheElementInHeader(DataTable dtLinks) {
        List<String> listLinks = dtLinks.asList(String.class);
        for (int i = 0; i < listLinks.size(); i++) {
            hs.clickFunction(hs.getWebElement(listLinks.get(i)));
        }
    }

    @And("Customer clicks on the element in Dialog Content")
    public void theCustomerClicksOnTheElementInDialog(DataTable dtButtons) {
        List<String> listButton = dtButtons.asList(String.class);
        for (int i = 0; i < listButton.size(); i++) {
            dc.clickFunction(dc.getWebElement(listButton.get(i)));
        }
    }

    @And("Customer sends keys in dialog")
    public void theCustomerSendsKeysInDialog(DataTable dtBoxAndTexts) {
        List<List<String>> listBoxAndTexts = dtBoxAndTexts.asLists(String.class);
        for (int i = 0; i < listBoxAndTexts.size(); i++) {
            WebElement box = (dc.getWebElement(listBoxAndTexts.get(i).get(0)));
            dc.sendKeysFunction(box, listBoxAndTexts.get(i).get(1));
        }
    }

    @And("Customer sends keys in header")
    public void theCustomerSendsKeysInHeader(DataTable dtBoxAndTexts) {
        List<List<String>> listBoxAndTexts = dtBoxAndTexts.asLists(String.class);
        for (int i = 0; i < listBoxAndTexts.size(); i++) {
            WebElement box = (hs.getWebElement(listBoxAndTexts.get(i).get(0)));
            hs.sendKeysFunction(box, listBoxAndTexts.get(i).get(1));
        }
    }

    @And("Customer confirms the text message")
    public void theCustomerConfirmsTheTextMessage(DataTable dtBoxAndTexts) {
        List<List<String>> listBoxAndTexts = dtBoxAndTexts.asLists(String.class);
        for (int i = 0; i < listBoxAndTexts.size(); i++) {
            WebElement box = (dc.getWebElement(listBoxAndTexts.get(i).get(0)));
            dc.verifyContainsText(box, listBoxAndTexts.get(i).get(1));
        }
    }
}

