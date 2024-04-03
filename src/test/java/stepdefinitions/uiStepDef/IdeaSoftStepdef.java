package stepdefinitions.uiStepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.IdeaSoftPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class IdeaSoftStepdef {
    IdeaSoftPage ideaPage = new IdeaSoftPage();
    private WebDriver driver;
   // private String baseUrl = "https://testcase.myideasoft.com/"; // Başlangıç URL'si
    private int urunSayisi = 5;

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
        ReusableMethods.waitForVisibility(ideaPage.sepeteEklenmistirAllert ,60);
        Assert.assertTrue(ideaPage.sepeteEklenmistirAllert.isDisplayed());
        ReusableMethods.waitForClickablility(ideaPage.sepeteEklenmistirAllert, 60);
        ideaPage.sepeteEklenmistirAllert.click();
        ReusableMethods.scrollPageDown();
    }

    //"5 adet urun sırayla sepete ekler
    @And("Ilgili urunden {int} adet olacak sekilde sepete eklenir")
    public void ılgiliUrundenAdetOlacakSekildeSepeteEklenir(int urunSayisi) throws InterruptedException {
        for (int i = 0; i < urunSayisi; i++) {
            try {
                ReusableMethods.waitForClickablility(ideaPage.sepeteEkle, 60);
                ReusableMethods.clickWithJS(ideaPage.sepeteEkle);
                // "Sepetinize Eklenmistir" uyarısını bekler ve görünür olduğunda devam eder
                yazisiGozlemlenir("Sepetinize Eklenmistir");
            } catch (Exception e) {
                System.out.println("Tiklanabilir Degil Tekrar Denenecek");;
            }
        }
    }

    // Sepet logosuna tıklayarak sepet içeriğini kontrol eder
    @And("Sepet iceriginde urunden {int} adet oldugu kontrol edilir")
    public void sepetIcerigindeUrundenAdetOlduguKontrolEdilir(int urunSayisi) {

        String adetMetni = ideaPage.adetButton.getText();
        int adet = 0;
        if (!adetMetni.isEmpty()) {
            adet = Integer.parseInt(adetMetni);
        }

        Assert.assertEquals(5, adet);

        //   Assert.assertEquals(ideaPage.adetButton.getText(), Integer.toString(urunSayisi));
        //   Assert.assertEquals(5, Integer.parseInt(ideaPage.adetButton.getText()));
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
