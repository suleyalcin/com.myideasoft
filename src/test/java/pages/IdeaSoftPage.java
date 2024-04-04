package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class IdeaSoftPage {
    public IdeaSoftPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//input[@placeholder='Aramak istediğiniz ürünü yazınız']")
    public WebElement aramaButonu;

    @FindBy(xpath = "//a[normalize-space()='Ürün']")
    public WebElement urun;

    @FindBy(xpath = "//a[@class='add-to-cart-button']")
    public WebElement sepeteEkle;

    @FindBy(xpath = "//div[@class='showcase-container']//div[@class='row']")
    public WebElement sepeteEklenmistirAllert;

    @FindBy(xpath = "//button[normalize-space()='Ara']")
    public WebElement araButton;

    @FindBy(xpath = "//header/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/a[1]/*[1]")
    public WebElement sepet;

    @FindBy(xpath = "//div[@class='cart-amount']")
    public WebElement adetButton;
}
