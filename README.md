# Otonom Araç Navigasyonu İçin Güvenlik Modülü Tasarımı

Bu proje, otonom bir aracın iki farklı engel grubu arasından en güvenli şekilde geçmesini sağlayacak **Optimum Ayrıştırıcı Sınırı** bulmak için geliştirilmiştir. Projede, makine öğrenmesi algoritmalarından **Destek Vektör Makineleri (Support Vector Machines - SVM)** kullanılarak matematiksel bir modelleme ve Java uygulaması gerçekleştirilmiştir.

## 🚀 Projenin Amacı
İki boyutlu bir düzlemde tanımlanmış iki engel sınıfını sadece bir çizgiyle ayırmak yeterli değildir. Sistemin güvenliği için bu sınırın, her iki sınıftaki en yakın engel koordinatlarına olan uzaklığının **maksimum** olması gerekmektedir. Bu projede:
- İki sınıfı birbirinden en geniş "güvenlik koridoru" (margin) ile ayıracak algoritma tasarlanmıştır.
- Bulunan sınırın neden "optimum" olduğu matematiksel olarak analiz edilmiştir.
- Algoritmanın zaman karmaşıklığı incelenmiştir.

## 🧠 Matematiksel Model (SVM)
Projede kullanılan SVM modeli, sınıflar arasındaki mesafeyi ($2/\|w\|$) maksimize etmeyi hedefler. 
- **Karar Fonksiyonu:** $f(x) = w \cdot x + b = 0$
- **Optimizasyon:** Hinge Loss fonksiyonu ve L2 Regularization kullanılarak Gradyan İnişi (Stochastic Gradient Descent) yöntemiyle en iyi $w$ (ağırlık) ve $b$ (sapma) değerleri bulunur.
- **Güvenlik Koridoru:** Algoritma, sadece engellerden kaçmakla kalmaz, engellere en uzak mesafede durarak sensör gürültülerine ve ölçüm hatalarına karşı **dayanıklılık (robustness)** sağlar.

## 🛠️ Teknik Özellikler
- **Dil:** Java
- **Algoritma:** Stochastic Gradient Descent (SGD)
- **Kayıp Fonksiyonu:** Hinge Loss
- **Regülarizasyon:** L2 Regularization ($\lambda$ parametresi ile)

## 📊 Analiz
### Zaman Karmaşıklığı
- **Eğitim Aşaması (Training):** $O(E \cdot n \cdot d)$ (E: Epoch, n: Veri Sayısı, d: Boyut)
- **Karar Aşaması (Prediction):** $O(d)$ 
Sistem, gerçek zamanlı navigasyon sistemlerinde anlık karar verebilecek kadar ($O(1)$ hızında) verimli çalışmaktadır.

## 💻 Kurulum ve Çalıştırma
Projeyi yerel makinenizde çalıştırmak için:

1. Depoyu klonlayın:
   ```bash
   git clone https://github.com/kullaniciadin/otonom-arac-guvenlik-modulu.git

2. Proje dizinine gidin:
   ```bash
   cd otonom-arac-guvenlik-modulu

4. Java dosyasını derleyin ve çalıştırın:
   ```bash
   javac OtonomNavigasyonSVM.java
   java OtonomNavigasyonSVM

## 📝 Örnek Çıktı
   ```bash
========================================================
   OTONOM ARAC GUVENLIK MODULU - OPTIMUM SINIR ANALIZI  
========================================================
  [+] Bulunan Karar Dogrusu: 0.1245*x + -0.0982*y + 0.0453 = 0
--------------------------------------------------------
  [*] GUVENLIK KORIDORU GENISLIGI (MARGIN): 1.452301
  (Bu deger, engeller arasındaki en geniş güvenli mesafedir)
========================================================

