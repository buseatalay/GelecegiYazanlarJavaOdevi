import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


/*
 *  GRUP - 4
 *
 * Hüseyin Onur BURAL
 * Yusuf Kaan DEMİRBAŞ
 * Beyza GÖNCÜLER
 * Buse ATALAY
 *
 * */

public class Main {

    static double userBalance = 0;
    static double totalDeposit = 0;
    static double totalWithdraw = 0;
    static int totalBillsPaidCount = 0;
    static int totalTransactions = 0;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    final String USERNAME = "admin";
    final String PASSWORD = "1234";

    int attempts = 3;

    Scanner scanner = new Scanner(System.in);

    while(attempts > 0){
        System.out.println("Lütfen kullanıcı adınızı giriniz : ");
        String inputUsername = scanner.nextLine();

        System.out.println("Lütfen şifrenizi giriniz : ");
        String inputPassword = scanner.nextLine();

        if(inputUsername.equals(USERNAME) && inputPassword.equals((PASSWORD)))
        {
            System.out.println("Giriş Başarılı");
            break;
        }
        else{
            attempts--;
            System.out.println("Hatalı Giriş ! Kalan hakkınız : " + attempts);
        }
    }

    if(attempts == 0){
        System.out.println("3 kez yanlış şifre girdiniz ! Program sonlanıyor.");
        scanner.close();
        return;
    }

    while(true){
        System.out.println("\n===== BANKA MENÜSÜ =====");
        System.out.println("1- Para Yatır");
        System.out.println("2- Para Çek");
        System.out.println("3- Bakiye Görüntüle");
        System.out.println("4- Fatura Öde (Elektrik/Su/İnternet)");
        System.out.println("5- Çıkış");
        System.out.print("Seçiminizi yapın: ");

        int userSelection = scanner.nextInt();
        double userAmount;

        switch(userSelection){
            case 1 :
                System.out.println("Yatırmak istediğiniz tutarı giriniz : ");
                userAmount = scanner.nextDouble();
                if(userAmount > 0){
                    deposit(userAmount);
                }
                else{
                    System.out.println("Lütfen geçerli bir tutar giriniz !");
                }
                break;
            case 2:
                System.out.println("Çekmek istediğiniz tutarı giriniz : ");
                userAmount = scanner.nextDouble();
                if(userAmount < 0)
                {
                    System.out.println("Lütfen geçerli bir değer giriniz !");
                }
                else if(userAmount >= userBalance){
                    System.out.println("Yetersiz bakiye ! Mevcut bakiye : " + userBalance);
                }
                else{
                    withdraw(userAmount);
                }
                break;
            case 3:
                System.out.println("Güncel bakiyeniz : " + userBalance);
                totalTransactions++;
                break;
            case 4:
                System.out.println("1- Elektrik");
                System.out.println("2- Su");
                System.out.println("3- İnternet");

                int paybillType = scanner.nextInt();

                if(paybillType > 3 || paybillType < 1){
                    System.out.println("Yanlış seçim yaptınız ! Tekrar deneyiniz.");
                    break;
                }

                System.out.println("Fatura tutarını giriniz : ");
                userAmount = scanner.nextDouble();

                if(userAmount <= 0){
                    System.out.println("Lütfen geçerli bir değer giriniz !");
                    break;
                }

                if(userAmount > userBalance){
                    System.out.println("Yetersiz bakiye ! Mevcut bakiye : " + userBalance);
                    break;
                }
                payBill(paybillType,userAmount);
                break;
            case 5:
                printSummary();
                return;
            default:
                System.out.println("Geçersiz seçim yapıldı ! Tekrar deneyiniz.");
                break;
        }

    }
}


    public static void deposit(double amount){
        totalDeposit += amount;
        userBalance += amount;
        totalTransactions++;
        System.out.println("İşlem başarılı ! Güncel bakiyeniz : " + userBalance);
    }

    public static void withdraw(double amount){

        double commissionValue = 0;

        if(amount > 5000)
        {
            commissionValue = amount*0.02;
            amount += commissionValue;
        }

        totalWithdraw += amount;
        userBalance -= amount;
        totalTransactions++;

        System.out.println("İşlem başarılı ! Alınan komisyon bedeli : " + commissionValue + " Güncel bakiyeniz : " + userBalance);
    }

    public static void payBill(int billType, double amount){
        switch(billType){

            case 1:
                amount = amount*0.95;
                userBalance -= amount;
                totalBillsPaidCount++;
                totalTransactions++;

                System.out.println("İşlem başarılı. Fatura tipine göre indirim uygulandı." + amount +  "Güncel bakiye " + userBalance);
                break;
            case 2:
                amount = amount*0.97;
                userBalance -= amount;
                totalBillsPaidCount++;
                totalTransactions++;

                System.out.println("İşlem başarılı. Fatura tipine göre indirim uygulandı." + amount + "Güncel bakiye " + userBalance);
                break;
            case 3:
                amount = amount*0.98;
                userBalance -= amount;
                totalBillsPaidCount++;
                totalTransactions++;
                System.out.println("İşlem başarılı. Fatura tipine göre indirim uygulandı. Ödenen tutar : " + amount + "Güncel bakiye " + userBalance);
                break;
        }

    }

    public static void printSummary(){

        System.out.println("İşlem Özeti");
        System.out.println("Güncel Bakiye : " + userBalance);
        System.out.println("Toplam Yatırılan Miktar : " + totalDeposit);
        System.out.println("Toplam Çekilen Miktar : " + totalWithdraw);
        System.out.println("Ödenen Fatura Adedi : " + totalBillsPaidCount);
        System.out.println("Net Hareket Sayısı : " + totalTransactions);
    }

}

//