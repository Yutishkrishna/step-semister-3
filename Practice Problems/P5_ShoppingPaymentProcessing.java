import java.util.*;

public class P5_ShoppingPaymentProcessing {
    static class Customer {String name;Customer(String n){name=n;}}
    static class Product {
        String name; double price;
        Product(String n,double p){name=n;price=p;}
    }
    static class Item {
        Product product; int qty;
        Item(Product p,int q){product=p;qty=q;}
        double total(){return product.price*qty;}
    }
    interface PaymentMethod {
        boolean processPayment(double amount);
        String name();
    }
    static class CreditCardPayment implements PaymentMethod {
        public boolean processPayment(double amount){return true;}
        public String name(){return "Credit Card";}
    }
    static class PayPalPayment implements PaymentMethod {
        boolean success;
        PayPalPayment(boolean s){success=s;}
        public boolean processPayment(double amount){return success;}
        public String name(){return "PayPal";}
    }
    static class BankTransferPayment implements PaymentMethod {
        public boolean processPayment(double amount){return true;}
        public String name(){return "Bank Transfer";}
    }
    static class Order {
        enum Status { PENDING, PAID }
        String id; Customer customer; List<Item> items=new ArrayList<>();
        private Status status=Status.PENDING;
        Order(String id,Customer c){this.id=id;customer=c;System.out.println("Order created for "+c.name+".");}
        void add(Product p,int qty){items.add(new Item(p,qty));}
        double total(){return items.stream().mapToDouble(Item::total).sum();}
        void pay(PaymentMethod method){
            if(items.isEmpty()){
                System.out.println("Cannot process payment for an empty order.");
                return;
            }
            System.out.println("Payment initiated via "+method.name()+" for Order "+id+".");
            if(method.processPayment(total())){
                status=Status.PAID;
                System.out.println("Payment for Order "+id+" successful. Order status: Paid.");
            } else {
                System.out.println("Payment for Order "+id+" failed. Order status: Pending.");
            }
        }
    }
    public static void main(String[] args){
        Order x=new Order("X",new Customer("Customer X"));
        x.add(new Product("Product A",100),2); x.add(new Product("Product B",50),1);
        x.pay(new CreditCardPayment());
        Order y=new Order("Y",new Customer("Customer Y")); y.pay(new CreditCardPayment());
        Order z=new Order("Z",new Customer("Customer Z"));
        z.add(new Product("Product C",75),1); z.pay(new PayPalPayment(false));
    }
}