import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Product product1= new Product(1,"Lenovo v14",15000,"16 GB RAM",10); //obje oluşturma, örnek oluşturma, referans oluşturma
        ProductManager productManager=new ProductManager();
        productManager.addToCart(product1);

        Product product2= new Product();
       product2.setId(2);
       product2.setName("Lenovo V15");
       product2.setDetail("16 GB RAM");
       product2.setDiscount(10);
       product2.setUnitPrice(16000);

        System.out.println(product2.getUnitPriceAfterDiscount());

        Category category=new Category();
        category.setId(1);
        category.setName("İçecek");

        Category category2=new Category();
        category2.setId(2);
        category2.setName("Yiyecek");

        System.out.println(category.getName());
        System.out.println(category2.getName());

    }
}