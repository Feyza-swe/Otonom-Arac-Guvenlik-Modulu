import java.util.ArrayList;
import java.util.List;

class Point {
    final double x, y;
    final int label;

    public Point(double x, double y, int label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }
}

public class OtonomNavigasyonSVM {
    private final double[] weights = {0.0, 0.0};
    private double bias = 0.0;
    private final double learningRate = 0.001;
    private final double lambda = 0.01;

    public void train(List<Point> points, int epochs) {
        for (int epoch = 1; epoch <= epochs; epoch++) {
            for (Point p : points) {
                double condition = p.label * (weights[0] * p.x + weights[1] * p.y + bias);

                if (condition >= 1) {
                    weights[0] -= learningRate * (2 * lambda * weights[0]);
                    weights[1] -= learningRate * (2 * lambda * weights[1]);
                } else {
                    weights[0] -= learningRate * (2 * lambda * weights[0] - p.label * p.x);
                    weights[1] -= learningRate * (2 * lambda * weights[1] - p.label * p.y);
                    bias += learningRate * p.label;
                }
            }
        }
    }

    public void sonuclariYazdir() {
        // Güvenlik koridoru genişliği (Margin) = 2 / ||w||
        double wNorm = Math.sqrt(weights[0] * weights[0] + weights[1] * weights[1]);
        double margin = 2.0 / wNorm;

        System.out.println("\n========================================================");
        System.out.println("   OTONOM ARAC GUVENLIK MODULU - OPTIMUM SINIR ANALIZI  ");
        System.out.println("========================================================");
        System.out.printf("  [+] Bulunan Karar Dogrusu: %.4f*x + %.4f*y + %.4f = 0\n", 
                          weights[0], weights[1], bias);
        System.out.println("--------------------------------------------------------");
        System.out.printf("  [i] Agirlik katsayisi (w1) : %.6f\n", weights[0]);
        System.out.printf("  [i] Agirlik katsayisi (w2) : %.6f\n", weights[1]);
        System.out.printf("  [i] Sapma terimi (bias)    : %.6f\n", bias);
        System.out.println("--------------------------------------------------------");
        System.out.printf("  [*] GUVENLIK KORIDORU GENISLIGI (MARGIN): %.6f\n", margin);
        System.out.println("  (Bu deger, engeller arasındaki en geniş güvenli mesafedir)");
        System.out.println("========================================================\n");
    }

    public static void main(String[] args) {
        List<Point> veriSeti = new ArrayList<>();

        // Sınıf 1 (Engel A)
        veriSeti.add(new Point(1, 8, 1));
        veriSeti.add(new Point(2, 9, 1));
        veriSeti.add(new Point(1.5, 7.5, 1));

        // Sınıf 2 (Engel B)
        veriSeti.add(new Point(5, 2, -1));
        veriSeti.add(new Point(6, 1, -1));
        veriSeti.add(new Point(5.5, 1.5, -1));

        OtonomNavigasyonSVM svm = new OtonomNavigasyonSVM();
        
        System.out.println("[...] Algoritma eğitiliyor, lütfen bekleyiniz...");
        svm.train(veriSeti, 50000); // 50 bin iterasyon daha hassas sonuç verir
        
        // Hata düzeldi: Parametre göndermeden çağırıyoruz
        svm.sonuclariYazdir();
    }
}