package stepdefinitions.uiStepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.IdeaSoftPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class IdeaSoftStepdef {
    IdeaSoftPage ideaPage = new IdeaSoftPage();
    private WebDriver driver;


    // Kullanıcı bir siteye giriş yapar
    @Given("Kullanici ideaSoft sitesini ziyaret eder")
    public void kullaniciIdeaSoftSitesiniZiyaretEder() {
        Driver.getDriver().get(ConfigReader.getProperty("ideaSoftUrl"));
    }

    //Arama kutusuna "urun" kelimesini yazar ve arar
    @When("Arama kismina {string} yazarak arama yapar")
    public void aramaKisminaYazarakAramaYapar(String urun) {
        ideaPage.aramaButonu.sendKeys(urun + Keys.ENTER);
    }

    // Açılan sayfada "urun" adlı bir ürüne tıklar ve sepete ekler
    @When("Acilan sayfada {string} adli bir urune tiklar ve sepete ekler")
    public void acilanSayfadaAdliBirUruneTiklarVeSepeteEkler(String arg0) throws InterruptedException {
        ReusableMethods.waitForClickablility(ideaPage.urun, 30);
        ReusableMethods.clickWithJS(ideaPage.sepeteEkle);
    }

    //" SEPETİNİZE EKLENMİŞTİR " uyarısını kontrol eder
    @Then("{string} yazisi gozlemlenir")
    public void yazisiGozlemlenir(String arg0) throws InterruptedException {
        ReusableMethods.waitForVisibility(ideaPage.sepeteEklenmistirAllert, 30);
        Assert.assertTrue(ideaPage.sepeteEklenmistirAllert.isDisplayed());
        ReusableMethods.scrollPageDown();
    }

    //"4 adet urun daha sırayla sepete ekler
    @And("Ilgili urunden {int} adet olacak sekilde sepete eklenir")
    public void ılgiliUrundenAdetOlacakSekildeSepeteEklenir(int urunSayisi) throws InterruptedException {
        for (int i = 1; i < urunSayisi; i++) {
            try {
                aramaKisminaYazarakAramaYapar("urun");
                acilanSayfadaAdliBirUruneTiklarVeSepeteEkler("urun");
                yazisiGozlemlenir("SEPETİNİZE EKLENMİŞTİR");
            } catch (Exception e) {
                System.out.println("Tiklanabilir Degil Tekrar Denenecek");
                ;
            }
        }
    }

    // Sepet logosuna tıklayarak sepet içeriğini kontrol eder
    @And("Sepet iceriginde urunden {int} adet oldugu kontrol edilir")
    public void sepetIcerigindeUrundenAdetOlduguKontrolEdilir(int expectedAdet) throws InterruptedException {
        ReusableMethods.scrollUpWithJS();
        ideaPage.sepet.click();
        ReusableMethods.waitForClickablility(ideaPage.adetButton, 30);
        String adetMetni = ideaPage.adetButton.getText();
        System.out.println("Adet Metni {" + adetMetni + "}");
        int actualAdet = Integer.parseInt(adetMetni.trim());
        Assert.assertEquals(expectedAdet, actualAdet);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
