public class StockObserver implements Observer {
    private double ibmPrice, aaplePrice, googPrice;
    public static int observerIdTracker = 0;
    private int observerId;

    private Subject stockGrabber;

    public StockObserver(Subject stockGrabber){
        this.stockGrabber = stockGrabber;
        this.observerId = ++observerIdTracker;
        System.out.println("New observer "+this.observerId);
        stockGrabber.register(this);
    }
    @Override
    public void update(double ibmPrice, double aaplePrice, double googPrice) {
        this.ibmPrice = ibmPrice;
        this.aaplePrice = aaplePrice;
        this.googPrice = googPrice;
        printPrice();
    }

    public void printPrice(){
        System.out.println(
                observerId+"\nIBM: "+ibmPrice
                        +"\nGOOGLE: "+googPrice
                        +"\nAPPLE: "+aaplePrice+"\n"

        );
    }
}
