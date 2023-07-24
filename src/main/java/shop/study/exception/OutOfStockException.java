package shop.study.exception;

public class OutOfStockException extends RuntimeException{ //296p

    public OutOfStockException(String message) {
        super(message);
    }
}
