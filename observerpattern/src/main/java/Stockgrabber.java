import java.util.ArrayList;

public class Stockgrabber implements Subject{
    private ArrayList<Observer> observers;
    private double ibmPrice;
    private double aaplePrice;
    private double googPrice;

    public Stockgrabber() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);

    }

    @Override
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        System.out.println("observer "+observerIndex+" deleted");
        observers.remove(observerIndex);
    }

    @Override
    public void notifiyObserver() {

        for (Observer observer:observers) {
            observer.update(ibmPrice,aaplePrice,googPrice);
        }

    }

    public void setIbmPrice(double newIbmPrice){
        this.ibmPrice = newIbmPrice;
        notifiyObserver();
    }
    public void setAaplePrice(double newAaplePrice){
        this.aaplePrice = newAaplePrice;
        notifiyObserver();
    }
    public void setGoogPrice(double newGooglePrice){
        this.googPrice = newGooglePrice;
        notifiyObserver();
    }
}
