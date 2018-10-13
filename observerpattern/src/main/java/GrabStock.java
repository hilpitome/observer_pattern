public class GrabStock {
    public static void main(String[] args){
        Stockgrabber stockgrabber = new Stockgrabber();

        StockObserver observer1 = new StockObserver(stockgrabber);

        stockgrabber.setAaplePrice(100);
        stockgrabber.setGoogPrice(456);
        stockgrabber.setIbmPrice(876);

    }
}
