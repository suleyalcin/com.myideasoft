@test
Feature: Urun Arama ve Sepete Ekleme
  #https://testcase.myideasoft.com/ sitesi ziyaret edilir.
  #Arama kısmına "ürün" yazılarak arama yapılır.
  #Arama sonucunda listelenen ürünün detayına girilir ve ilgili üründen 5 adet sepete eklenir.
  #"SEPETİNİZE EKLENMİŞTİR" yazısının görünmesi kontrol edilir.
  #/sepet sayfasına gidilir ve sepet içeriğinde ilgili üründen 5 adet olduğu kontrol edilir.

  Scenario: Urun Arama ve Sepete Ekleme
    Given Kullanici ideaSoft sitesini ziyaret eder
    When Arama kismina "urun" yazarak arama yapar
    When Acilan sayfada "urun" adli bir urune tiklar ve sepete ekler
    Then "Sepetinize Eklenmistir" yazisi gozlemlenir
    And Ilgili urunden 5 adet olacak sekilde sepete eklenir
    And Sepet iceriginde urunden 5 adet oldugu kontrol edilir



